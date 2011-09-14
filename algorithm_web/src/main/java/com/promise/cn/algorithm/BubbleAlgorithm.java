/**@文件名: BubbleAlgorithm.java @作者： promisePB xingjian@yeah.net @日期 2011-5-9 下午10:44:25 */

package com.promise.cn.algorithm;

/**   
 * @类名: BubbleAlgorithm.java 
 * @包名: com.promise.cn.algorithm
 * @描述: 冒泡法 
 * @作者： promisePB xingjian@yeah.net   
 * @日期： 2011-5-9 下午10:44:25 
 * @版本： V1.0   
 */

public class BubbleAlgorithm {

	/**
	 * 功能：冒泡法
	 * 描述：传入object,对object集合进行排序
	 * @param object
	 */
	public Object bubble(Object object){
		return null;
	} 
	
	/**
	 * 
	 * 功能：采用冒泡法对数组排序(整型)
	 * 描述：当isDesc为true,从大到小排序，为false,从小到大
	 * @param array
	 * @param isDesc 是否递减
	 * @return 将排序好的集合返回
	 */
	public int[] bubbleForInt(int[] array,boolean isDesc){
		
		for(int i=0;i<array.length;i++){
			for(int j=1;j<array.length-i;j++){
				if(isDesc){//降序
					if(array[j-1]<array[j]){
						int temp = array[j-1];
						array[j-1] = array[j];
						array[j] = temp;
					}
				}else{//升序
					if(array[j-1]>array[j]){
						int temp = array[j-1];
						array[j-1] = array[j];
						array[j] = temp;
					}
				}
			}
		}
		return array;
	}

}
