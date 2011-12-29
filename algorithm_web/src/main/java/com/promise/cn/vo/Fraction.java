/*@文件名: Fraction.java  @创建人: 邢健   @创建日期: 2011-12-28 上午9:23:56*/
package com.promise.cn.vo;

/**   
 * @类名: Fraction.java 
 * @包名: com.promise.cn.vo 
 * @描述: 分数表示
 * @作者: 邢健 xingjian@yeah.net   
 * @日期: 2011-12-28 上午9:23:56 
 * @版本 V1.0   
 */
public class Fraction {

	//分子
	private int numerator;
	//分母
	private int denominator;
	//状态
	private boolean state;
	//是否是非基变量
	private boolean isBaseVar = false;
	
	public Fraction(int numerator,int denominator){
		this.numerator = numerator;
		this.denominator = denominator;
		if(denominator==0){
			this.state = false;
		}else{
			this.state = true;
			int number = getNumber(this.numerator,this.denominator);
			if(number!=1){
				this.numerator = this.numerator/number;
				this.denominator = this.denominator/number;
			}
		}
	}
	
	public Fraction(int numerator,int denominator,boolean isBaseVar){
		this.numerator = numerator;
		this.denominator = denominator;
		this.isBaseVar = isBaseVar;
		if(denominator==0){
			this.state = false;
		}else{
			this.state = true;
			int number = getNumber(this.numerator,this.denominator);
			if(number!=1){
				this.numerator = this.numerator/number;
				this.denominator = this.denominator/number;
			}
		}
	}
	
	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int getNumerator() {
		return numerator;
	}
	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}
	public int getDenominator() {
		return denominator;
	}
	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}
	
	@Override
	public String toString(){
		return this.numerator+"/"+this.denominator;
	}
	
	public double calcResult(){
		return (double)numerator/(double)denominator;
	}
	
	/**
	 * 0表示结果结果分子为0,1表示相等，-1表示小于零
	 * @return
	 */
	public String calcResultType(){
		if(numerator==0&&denominator!=0){
			return "0";
		}else if(denominator!=0&&numerator!=0&&denominator==numerator){
			return "1";
		}else if((numerator/denominator)<0){
			return "-1";
		}else if(denominator==0){
			return "max";
		}
		return "unknown";
	}
	
	public int getNumber(int up,int down){//求最大公约数
		up = (up<0)?-up:up;
		down = (down<0)?-down:down;
		int min; 
		int tem=1;
		min=(up<down)? up:down ; 
		for(int i=1;i<=min;i++) { 
		if(up%i==0&&down%i==0) 
			tem=i ; 
		} 
		return tem ; 
	} 
	
	public boolean isBaseVar() {
		return isBaseVar;
	}

	public void setBaseVar(boolean isBaseVar) {
		this.isBaseVar = isBaseVar;
	}
}
