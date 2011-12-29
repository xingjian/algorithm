/*@文件名: SimplexAlgorithm.java  @创建人: 邢健   @创建日期: 2011-12-26 下午4:05:42*/
package com.promise.cn.algorithm;

import com.promise.cn.vo.Fraction;
import com.promise.cn.vo.LPModel;

/**   
 * @类名: SimplexAlgorithm.java 
 * @包名: com.promise.cn.algorithm 
 * @描述: 单纯形法 
 * @作者: 邢健 xingjian@yeah.net   
 * @日期: 2011-12-26 下午4:05:42 
 * @版本 V1.0   
 */
public class SimplexAlgorithm {

	//约束条件x的系数矩阵
	public Fraction[][] unitArray;
	//目标函数x的系数
	public Fraction[] cjArray;
	//基解
	public Fraction[] baseArray;
	//Cb
	public Fraction[] cbArray;
	//Xb
	public String[] xbArray;
	//b
	public Fraction[] bArray;
	//sbArray
	public Fraction[] siArray;
	//行大小,列大小
	public int m,n;
	/**
	 * 记录运算过程中的信息
	 */
	public StringBuffer content = new StringBuffer();
	
	/**
	 * 初始化数据，通过解析LPModel
	 * @param lpModel
	 */
	public void initRequiredData(LPModel lpModel){
		//转换成标准
		lpModel.changeLPModleToStandard();
		n = lpModel.xCount;
		m = lpModel.getRestraintCondition().size();
		cbArray = new Fraction[m];
		siArray = new Fraction[m];
		//Xb
		xbArray = new String[m];
		//b
		bArray = new Fraction[m];
		unitArray = new Fraction[m][n];
		//初始化unitArray
		for(int i=0;i<m;i++){
			String rcTemp = lpModel.getRestraintCondition().get(i);
			bArray[i] = new Fraction(Integer.parseInt(rcTemp.substring(0,rcTemp.indexOf(","))),1);
			for(int j=0;j<n;j++){
				if(rcTemp.indexOf("x"+(j+1))!=-1){
					String subStr1 = rcTemp.substring(0, rcTemp.indexOf("x"+(j+1)));
					String numeratorStr = rcTemp.substring(subStr1.lastIndexOf(",")+1, rcTemp.indexOf("x"+(j+1))-1);
					Fraction fraction = new Fraction(Integer.parseInt(numeratorStr),1);
					unitArray[i][j] = fraction;
				}else{
					unitArray[i][j] = new Fraction(0,1);
				}
			}
		}
		//初始化cjArray
		String[] tfArray = lpModel.getTargetFunction().split(",");
		cjArray = new Fraction[n];
		baseArray = new Fraction[n];
		for(int i=1;i<tfArray.length;i++){
			cjArray[i-1] = new Fraction(Integer.parseInt(tfArray[i].split(":")[0]),1);
			baseArray[i-1] = new Fraction(Integer.parseInt(tfArray[i].split(":")[0]),1,false);
		}
		//初始化cbArray,xbArray
		for(int k=0;k<m;k++){
			for(int a=0;a<baseArray.length;a++){
				if(baseArray[a].calcResultType().equals("0")){
					baseArray[a].setBaseVar(true);
					for(int j=0;j<m;j++){
						if(unitArray[j][a].calcResultType().equals("1")){
							cbArray[j] = new Fraction(baseArray[m].getNumerator(),baseArray[m].getDenominator());
							xbArray[j] = "x"+(a+1);
						}
					}
				}
			}
		}
	}
	
	//开始计算核心算法
	public void calcSimplex(){
		int indexMaxBaseArray = getIndexArrayMaxAndMin(baseArray,true);
		if(baseArray[indexMaxBaseArray].calcResult()>0){//非基变量检验数大于0(继续换基)
			for(int i=0;i<siArray.length;i++){
				siArray[i] = new Fraction(bArray[i].getNumerator()*unitArray[i][indexMaxBaseArray].getDenominator(),bArray[i].getDenominator()*unitArray[i][indexMaxBaseArray].getNumerator());
			}
			int indexMinSiArray = getSiArrayMinValueIndex(siArray);
			if(indexMinSiArray==-1){
				System.out.println("无解");
			}else{
				Fraction fracTemp = unitArray[indexMinSiArray][indexMaxBaseArray];
				for(int c=0;c<n;c++){
					unitArray[indexMinSiArray][c] = getFractionDivision(unitArray[indexMinSiArray][c],fracTemp);
				}
				//更换bArray
				bArray[indexMinSiArray] = getFractionDivision(bArray[indexMinSiArray],fracTemp);
				//更换unitArray
				for(int a=0;a<m;a++){
					if(a!=indexMinSiArray){
						Fraction fTemp = new Fraction(unitArray[a][indexMaxBaseArray].getNumerator(),unitArray[a][indexMaxBaseArray].getDenominator());
						for(int b=0;b<n;b++){
							Fraction f1 = unitArray[indexMinSiArray][b];
							Fraction f3 = unitArray[a][b];
							unitArray[a][b] = getFractionCalc(f1,fTemp,f3);
						}
						bArray[a] = getFractionCalc(bArray[indexMinSiArray],fTemp,bArray[a]);
					}
				}
				//baseArray
				Fraction fTempBase = new Fraction(baseArray[indexMaxBaseArray].getNumerator(),baseArray[indexMaxBaseArray].getDenominator());
				for(int w=0;w<baseArray.length;w++){
					baseArray[w] = getFractionCalc(unitArray[indexMinSiArray][w],fTempBase,baseArray[w]);
				}
				//最重要一步换基
				cbArray[indexMinSiArray] = new Fraction(cjArray[indexMaxBaseArray].getNumerator(),cjArray[indexMaxBaseArray].getDenominator());
				xbArray[indexMinSiArray] = "x"+(indexMaxBaseArray+1);
				//开始循环
				calcSimplex();
			}
		}else if(baseArray[indexMaxBaseArray].calcResult()<0){//最优解唯一
			System.out.println("最优解唯一");
			for(int i=0;i<xbArray.length;i++){
				System.out.println(xbArray[i]+":"+bArray[i].toString());
			}
		}else if(baseArray[indexMaxBaseArray].calcResult()==0){//无穷多最优解
			System.out.println("无穷多最优解");
			for(int i=0;i<xbArray.length;i++){
				System.out.println(xbArray[i]+":"+bArray[i].toString());
			}
		}
	}
	
	/**
	 * 返回分数数组最大值最小值坐标
	 * isMax true 返回最大值索引值，isMax false 返回最小值索引值
	 * 比较值时候不用等于根据勃兰特规则（防止发生退化现象）
	 * @param array
	 * @return
	 */
	public int getIndexArrayMaxAndMin(Fraction[] array,boolean isMax){
		int index = 0;
		double value = array[0].calcResult();
		if(array.length==1){
			return 0;
		}else{
			if(isMax){
				for(int i=1;i<array.length;i++){
					if(array[i].isState()&&array[i].calcResult()>value&&array[i].isBaseVar()==false){
						index = i;
						value = array[i].calcResult();
					}
				}
			}else{
				for(int i=1;i<array.length;i++){
					if(array[i].isState()&&array[i].calcResult()<value&&array[i].isBaseVar()==false){
						index = i;
						value = array[i].calcResult();
					}
				}
			}
			
		}
		return index;
	}
	
	/**
	 * 
	 * 返回分数数组最小值坐标并且大于0
	 * isMax true 返回最大值索引值，isMax false 返回最小值索引值
	 * 比较值时候不用等于根据勃兰特规则（防止发生退化现象）
	 * @param array
	 * @return
	 */
	public int getSiArrayMinValueIndex(Fraction[] array){
		int index = -1;
		double value = 0;
		boolean valueBoolean = false;
		for(int i=0;i<array.length;i++){
			if(array[i].isState()&&array[i].calcResult()>0&&!valueBoolean){
				value = array[i].calcResult();
				index = i;
				valueBoolean = true;
			}else if(array[i].isState()&&array[i].calcResult()>0&&valueBoolean&&array[i].calcResult()<value){
				value = array[i].calcResult();
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * 三个分数做运算，前两个做乘法，在和最后一个做加法
	 * 返回新的分数
	 */
	public Fraction getFractionCalc(Fraction f1,Fraction f2,Fraction f3){
		int a = f1.getDenominator()*f2.getDenominator()*f3.getNumerator()-f1.getNumerator()*f2.getNumerator()*f3.getDenominator();
		int b = f1.getDenominator()*f2.getDenominator()*f3.getDenominator();
		return new Fraction(a,b);
	}
	/**
	 * 两个分数相处其实乘以倒数
	 */
	public Fraction getFractionDivision(Fraction f1,Fraction f2){
		int a = f1.getNumerator()*f2.getDenominator();
		int b = f1.getDenominator()*f2.getNumerator();
		return new Fraction(a,b);
	}
}
