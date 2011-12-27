/*@文件名: SimplexAlgorithm.java  @创建人: 邢健   @创建日期: 2011-12-26 下午4:05:42*/
package com.promise.cn.algorithm;

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

	public int[][] unitArray;
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
		int n = lpModel.xCount;
		int m = lpModel.getRestraintCondition().size();
		unitArray = new int[m][n];
		//初始化unitArray
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				String rcTemp = lpModel.getRestraintCondition().get(i);
				if(rcTemp.indexOf("x"+(j+1))!=-1){
					unitArray[i][j] = 1;
				}else{
					unitArray[i][j] = 0;
				}
			}
		}
	}
}
