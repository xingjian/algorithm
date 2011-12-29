/*@文件名: PBMathUtil.java  @创建人: 邢健   @创建日期: 2011-12-29 下午5:49:53*/
package com.promise.cn.util;

/**   
 * @类名: PBMathUtil.java 
 * @包名: com.promise.cn.util 
 * @描述: 常用的数学方法 
 * @作者: 邢健 xingjian@yeah.net   
 * @日期: 2011-12-29 下午5:49:53 
 * @版本 V1.0   
 */
public class PBMathUtil {

	/**
	 * 最大公约数
	 * @param up
	 * @param down
	 * @return
	 */
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
}
