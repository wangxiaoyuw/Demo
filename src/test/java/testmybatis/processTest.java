package testmybatis;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:activiti.cfg.xml"})

public class processTest {

	@Test
	public void test1(){
		//自动读取配置文件加载对应的资源
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Deployment deployment = processEngine.getRepositoryService().createDeployment().name("leave").addClasspathResource("leave.bpmn").addClasspathResource("leave.bpmn").deploy();
		System.out.println("部署Id："+deployment.getId()); // 部署Id
		System.out.println("部署名称："+deployment.getName()); // 部署名称


		//启动流程
		String processDefinitionKey = "myProcess_1"; // 使用Key的启动，默认按照对新版本的流程定义启动    <process id="myProcess_1"
		ProcessInstance pi = processEngine.getRuntimeService() // 与正在执行的流程实例和执行对象相关的Service
				.startProcessInstanceByKey(processDefinitionKey); // 使用流程定义的Key启动流程实例，key对应helloworld.bpmn文件中的流程名称

		System.out.println("流程实例Id"+pi.getId()); // 流程实例Id
		System.out.println("流程定义Id"+pi.getProcessDefinitionId()); // 流程定义Id

        /** 查询当前人的个人任务*/
		String assignee = "张三";
		List<Task> list = processEngine.getTaskService().createTaskQuery().taskAssignee(assignee).list();
        if (list !=null && list.size()>0){
        	for (Task task :list){
				System.out.println("任务Id："+task.getId());
				System.out.println("任务名称："+task.getName());
				System.out.println("任务的创建时间："+task.getCreateTime());
				System.out.println("任务的办理人:"+task.getAssignee());
				System.out.println("流程实例Id:"+task.getProcessInstanceId());
				System.out.println("执行对象Id:"+task.getExecutionId());
				System.out.println("流程定义Id:"+task.getProcessDefinitionId());
				System.out.println("#####################################################");
			}
		}
		  		/**完成我的任务*/
		/*String taskId = "47508";
		processEngine.getTaskService()//与正在执行的任务管理相关的Service
				.complete(taskId);
		System.out.println("完成任务：任务Id"+taskId);*/


		/** 查询流程状态（判断流程正在执行还是结束）*/
			/*String processInstanceId = "";

			ProcessInstance pi1 = processEngine.getRuntimeService()
					.createProcessInstanceQuery()// 创建一个流程实例查询
					.processInstanceId(processInstanceId) // 使用流程实例Id查询
					.singleResult();*/

			// 获取流程实例查询不到（1）
			// 或者获取流程实例历史，查询结束时间ok（2）
			/*if (pi1 == null) {
				System.out.println("流程已经结束");
			} else {
				System.out.println("流程没有结束");
			}*/

			/** 查询历史任务 */
		/*List<HistoricTaskInstance> list1 = processEngine.getHistoryService() // 与历史数据相关的Service
				.createHistoricTaskInstanceQuery()// 历史任务表
				.taskAssignee(assignee) // 设置对应人
				.list();

		if (list!=null&& list.size() > 0) {
			for (HistoricTaskInstance hti : list1) {
				System.out.println(hti.getId()+"" +hti.getName() +"" +hti.getProcessInstanceId() +" "+hti.getStartTime()
						+""+hti.getEndTime() +"" +hti.getDurationInMillis());
				System.out.println("#################################################");
			}
		}*/

		/** 查询历史流程实例 */
		/*String processInstanceId1 = "55008";
		HistoricProcessInstance hpi = processEngine.getHistoryService() //  与历史数据相关的Service
				.createHistoricProcessInstanceQuery() // 创建流程实例查询表
				.processInstanceId(processInstanceId1)
				.singleResult();

		System.out.println(hpi);*/


		processEngine.close();
	}

}
