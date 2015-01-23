/*@文件名: JavaTest.java  @创建人: 邢健   @创建日期: 2011-12-29 上午10:23:25*/
package algorithm;

/**   
 * @类名: JavaTest.java 
 * @包名: algorithm 
 * @描述: java测试使用 
 * @作者: 邢健 xingjian@yeah.net   
 * @日期: 2011-12-29 上午10:23:25 
 * @版本 V1.0   
 */
public class JavaTest {

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
	
	public double divMethod(int a, int b){
		return (double)a/(double)b;
	}
	
	public static void main(String[] args) {
		JavaTest jt = new JavaTest();
		System.out.println(jt.divMethod(64, 256));
		System.out.println(jt.getNumber(64,256));
		System.out.println(jt.getNumber(0,-256));
		System.out.println(Double.parseDouble("-3"));
	}
	
	/**
	 * 正则表达式
	 */
	public void testStr(){
		
	}
	
}
