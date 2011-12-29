/*@文件名: LinearProgramTest.java  @创建人: 邢健   @创建日期: 2011-12-29 下午5:03:13*/
package algorithm;

import org.junit.Test;

import com.promise.cn.algorithm.LinearProgram;

/**   
 * @类名: LinearProgramTest.java 
 * @包名: algorithm 
 * @描述: LinearProgram测试用例 
 * @作者: 邢健 xingjian@yeah.net   
 * @日期: 2011-12-29 下午5:03:13 
 * @版本 V1.0   
 */
public class LinearProgramTest {

	private LinearProgram lp;
	
	@Test
	public void testSolve(){
		double a[][] = {{1,-2,1,11},{-2,0,1,1},{-4,1,2,3}};//系数矩阵
		int x[] = {-3,1,1};//目标函数的价值系数
		lp = new LinearProgram(-1,3,3,1,1,1,a,x);
		lp.solve();
		
		double b[][] = {{1,2,8},{4,0,16},{0,4,12}};//系数矩阵
		int x1[] = {2,3};//目标函数的价值系数
		lp = new LinearProgram(1,3,2,3,0,0,b,x1);
		lp.solve();
		
		double c[][] = {{1,2,2,1,0,8},{3,4,1,0,1,7}};//系数矩阵
		int x2[] = {5,2,3,-1,1};//目标函数的价值系数
		lp = new LinearProgram(1,2,5,0,2,0,c,x2);
		lp.solve();
		
		double d[][] = {{1,0,4},{0,1,3},{1,2,8}};//系数矩阵
		int x3[] = {-1,-2};//目标函数的价值系数
		lp = new LinearProgram(-1,3,2,3,0,0,d,x3);
		lp.solve();
		
		double e[][] = {{1,1,1,6},{1,0,-1,4},{0,1,-1,3}};//系数矩阵
		int x4[] = {3,2,1};//目标函数的价值系数
		lp = new LinearProgram(-1,3,3,1,0,2,e,x4);
		lp.solve();
	}
}
