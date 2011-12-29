/*@文件名: LPModel.java  @创建人: 邢健   @创建日期: 2011-12-27 下午1:52:10*/
package com.promise.cn.vo;

import java.util.List;

/**   
 * @类名: LPModel.java 
 * @包名: com.promise.cn.vo 
 * @描述: 线性规划模型 
 * @作者: 邢健 xingjian@yeah.net   
 * @日期: 2011-12-27 下午1:52:10 
 * @版本 V1.0   
 */
@SuppressWarnings("all")
public class LPModel {

	/**
	 * 目标函数
	 * 最大值格式('max',2:x1,3:x2)表示max z = 2*x1+3*x2;
	 * 最小值格式('min',2:x1,3:x2)表示min z = 2*x1+3*x2
	 */
	private String targetFunction;
	/**
	 * 多个约束条件
	 * 大于约束条件的格式(8,'than',1:x1,2:x2)表示8>=x1+2*x2
	 * 等于约束条件的格式(8,'equal',1:x1,2:x2)表示8=x1+2*x2
	 * 小于约束条件的格式(8,'less',1:x1,2:x2)表示8=<x1+2*x2
	 */
	private List<String> restraintCondition;
	/**
	 * 输出内容
	 */
	public StringBuffer content = new StringBuffer();
	/**
	 *x参数个数 
	 */
	public int xCount;
	
	/**
	 * 构造函数
	 * @param targetFunction
	 * @param restraintCondition
	 */
	public LPModel(String targetFunction,List<String> restraintCondition){
		this.targetFunction = targetFunction;
		this.restraintCondition = restraintCondition;
	}

	/**
	 * 数学模型格式展示目标函数（有可能是非标准的）
	 * 返回值如果为1，表示可以正常展示；0表示不可以展示
	 */
	public String toStringMathTargetFunction(){
		if(null!=targetFunction&&!targetFunction.trim().equals("")){
			String[] strArray = targetFunction.split(",");
			if(strArray[0].equals("max")){//大于
				content.append("目标函数 Max z = ");
			}else if(strArray[0].equals("min")){//等于
				content.append("目标函数 Min z = ");
			}
			for(int i=1;i<strArray.length;i++){
				String cx = strArray[i];
				String[] cxArray = cx.split(":");
				if(i==1){
					content.append(cxArray[0]+"*"+cxArray[1]);
				}else{
					if(Double.parseDouble(cxArray[0])>=0){//是正数
						content.append("+"+cxArray[0]+"*"+cxArray[1]);
					}else{//是负数
						content.append(cxArray[0]+"*"+cxArray[1]);
					}
				}
				
			}
			content.append("\t\n");
			return "1";
		}
		return "0";
	}
	
	/**
	 * 数学模型格式展示约束条件（有可能是非标准的）
	 * 返回值如果为1，表示可以正常展示；0表示不可以展示
	 */
	public String toStringMathRestraintCondition(){
		if(null!=restraintCondition&&restraintCondition.size()>0){
			content.append("约束条件：\t\n");
			for(int i=0;i<restraintCondition.size();i++){
				String rcStr = restraintCondition.get(i);
				String[] rcArray = rcStr.split(",");
				for(int j=2;j<rcArray.length;j++){
					String rcsubStr = rcArray[j];
					String[] rcsubArray = rcsubStr.split(":");
					if(j==2){
						content.append(rcsubArray[0]+"*"+rcsubArray[1]);
					}else{
						if(Double.parseDouble(rcsubArray[0])>0){//是正数
							content.append("+"+rcsubArray[0]+"*"+rcsubArray[1]);
						}else{//是负数
							content.append(rcsubArray[0]+"*"+rcsubArray[1]);
						}
					}
				}
				if(rcArray[1].equals("than")){//大于
					content.append(" =< "+rcArray[0]);
				}else if(rcArray[1].equals("equal")){
					content.append(" = "+rcArray[0]);
				}else if(rcArray[1].equals("less")){
					content.append(" >= "+rcArray[0]);
				}
				content.append("\t\n");
			}
		}
		return "0";
	}
	
	/**
	 * 将LPModel转换成标准的,可以用单纯形法
	 * 返回值如果为1，表示转换标准成功；0表示标准成功失败
	 */
	public String changeLPModleToStandard(){
		int x_MaxIndex = targetFunction.split(",").length-1;
		int addX = 0;
		for(int i=0;i<restraintCondition.size();i++){
			String rcStr = restraintCondition.get(i);
			String[] rcArray = rcStr.split(",");
			if(!rcArray[1].equals("equal")){//不等于
				addX++;
				targetFunction = targetFunction+",0:x"+(x_MaxIndex+addX);
				if(rcArray[1].equals("than")){
					String str1 = rcStr.replace("than", "equal");
					str1 = str1+",1:x"+(x_MaxIndex+addX);
					restraintCondition.set(i,str1);
				}else if(rcArray[1].equals("less")){
					String str1 = rcStr.replace("less", "equal");
					str1 = str1+",-1:x"+(x_MaxIndex+addX);
					restraintCondition.set(i,str1);
				}
			}
		}
		xCount = x_MaxIndex+addX;
		if(addX>0){
			content.append("应该添加松弛变量：x"+(x_MaxIndex+1)+"---x"+(x_MaxIndex+addX)+"\t\n");
		}else{
			content.append("不用添加松弛变量："+"\t\n");
		}
		return "0";
	}
	
	@Override
	public String toString(){
		String retStr = targetFunction+"\t\n";
		for(int i=0;i<restraintCondition.size();i++){
			retStr = retStr + restraintCondition.get(i)+"\t\n";
		}
		return retStr;
	}
	
	public String getTargetFunction() {
		return targetFunction;
	}

	public void setTargetFunction(String targetFunction) {
		this.targetFunction = targetFunction;
	}

	public List<String> getRestraintCondition() {
		return restraintCondition;
	}

	public void setRestraintCondition(List<String> restraintCondition) {
		this.restraintCondition = restraintCondition;
	}

}
