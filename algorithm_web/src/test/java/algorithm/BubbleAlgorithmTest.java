/**@文件名: BubbleAlgorithmTest.java @作者： promisePB xingjian@yeah.net @日期 2011-5-12 上午09:05:12 */

package algorithm;

import org.junit.Before;
import org.junit.Test;

import com.promise.cn.algorithm.BubbleAlgorithm;
import com.promise.cn.util.PrintUtil;

/**   
 * @类名: BubbleAlgorithmTest.java 
 * @包名: algorithm 
 * @描述: 冒泡法测试用例 
 * @作者： promisePB xingjian@yeah.net   
 * @日期： 2011-5-12 上午09:05:12 
 * @版本： V1.0   
 */

public class BubbleAlgorithmTest {

	private int[] int_array;			//测试数据
	private BubbleAlgorithm bubble;		//冒泡算法实例
	
	@Before
	public void preparedData(){
		int_array = new int[]{78,99,1,3,5,7,9,2,4,6,8,10};
		bubble = new BubbleAlgorithm();
	}
	
	@Test
	public void testBubbleForInt(){
		int[] result_array = bubble.bubbleForInt(int_array, true);
		PrintUtil.printObject(result_array);
		int[] result1_array = bubble.bubbleForInt(int_array, false);
		PrintUtil.printObject(result1_array);
	}
}
