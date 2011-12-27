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
	}
}
