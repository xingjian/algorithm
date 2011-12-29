package algorithm;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.promise.cn.algorithm.SimplexAlgorithm;
import com.promise.cn.vo.LPModel;
/**
* @类名: SimplexAlgorithmTest.java 
* @包名: algorithm 
* @描述: 单纯形测试用例
* @作者: 邢健 xingjian@yeah.net   
* @日期: 2011-12-27 下午2:59:27 
* @版本 V1.0
 */
@SuppressWarnings("all")
public class SimplexAlgorithmTest {

	private String targetFunction;
	private List<String> restraintCondition;
	private SimplexAlgorithm sa;
	
	/**
	 * 例题1 true
	 * targetFunction = "max,2:x1,3:x2";
	 * String[] strArray = {"8,than,1:x1,2:x2","16,than,4:x1","12,than,4:x2"};
	 * x(4,2,0,0,4)
	 * 例题2 true
	 * targetFunction = "max,2:x1,4:x2";
	 * String[] strArray = {"8,than,1:x1,2:x2","12,than,4:x2","12,than,3:x1"};
	 * 例题3 false
	 * targetFunction = "min,-3:x1,1:x2,1:x3";
	 * String[] strArray = {"11,than,1:x1,2:x2,1:x3","3,less,-4:x1,1:x2,2:x3","1,equal,-2:x1,1:x3"};
	 * 例题4 true
	 * targetFunction = "max,5:x1,2:x2,3:x3,-1:x4,1:x5";
	 * String[] strArray = {"8,equal,1:x1,2:x2,2:x3,1:x4","7,equal,3:x1,4:x2,1:x3,1:x5"};
	 * x(6/5,0,17/5,0,0)
	 */
	@Before
	public void preparedData(){
		targetFunction = "max,2:x1,3:x2";
		String[] strArray = {"8,than,1:x1,2:x2","16,than,4:x1","12,than,4:x2"};
		restraintCondition = new ArrayList<String>();
		for(int i=0;i<strArray.length;i++){
			restraintCondition.add(strArray[i]);
		}
		sa = new SimplexAlgorithm();
	}
	
	@Test
	public void testLPModel(){
		LPModel lpm = new LPModel(targetFunction, restraintCondition);
		String returnValue1 = lpm.toStringMathTargetFunction();
		String returnValue2 = lpm.toStringMathRestraintCondition();
		String returnValue3 = lpm.changeLPModleToStandard();
		String returnValue4 = lpm.toStringMathTargetFunction();
		System.out.println(lpm.getTargetFunction());
		String returnValue5 = lpm.toStringMathRestraintCondition();
		System.out.println(lpm.content.toString());
	}
	
	@Test
	public void testInitRequiredData(){
		LPModel lpm = new LPModel(targetFunction, restraintCondition);
		sa.initRequiredData(lpm);
		sa.calcSimplex();
	}
}
