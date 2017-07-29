# ibdaasdemo
本项目用于演示如何对接云蜂科技的数据api接口。

项目采用maven工程构建。

## 本项目以下三个功能：

- 提交任务
- 查询任务
- 补充任务

## 使用步骤如下：
1. 打开Config.java文件，修改三个变量“APPKEY”，“AK”和“SK”，将云蜂分配的填写进去。
2. 打开TaskSubmit.java文件，修改变量“taskType”为对应的值，确保该功能已经开通。填入需要提供的参数，具体可以参见api doc的各个数据页面提交任务章节。
3. 任务提交成功后，会得到response，里面可以获取“taskNo”
4. 如果需要查询任务状态，打开TaskQuery.java文件，修改“taskNo”变量为第3步返回的taskNo值。运行，通过response可以获得该任务的状态信息。
5. 对于pending状态任务，需要补充任务信息，打开TaskPatch.java文件，修改“taskNo”字段以及填写相应的“patchCode”及数据值，运行。该文件中给了各个patchCode的示例代码。

## 目录结构及文件说明：

- com.ibeesaas.demo
	- TaskPatch：补充任务
	- TaskQuery：查询任务
	- TaskSubmit：提交任务
- com.ibeesaas.demo.util
	- Config：存放全局变量，包括appkey，ak，sk等
	- TaskUtil：任务操作的工具，封装了提交，查询，补充任务三个方法。
	- TokenHelper：token鉴权的生成类
	- HttpClientUtil：封装了get和post的http请求方法
	- JsonUtils：json和类的转换工具类
	- Imagebase64：base64字符串转换并存储为文件，将文件内容转为base64字符串

