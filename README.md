#### mizhou-commons
所有服务依赖的工具类。

#####  修改版本号流程

1.  只更新父模块的版本号;
	```shell
	mvn versions:set -DnewVersion=1.7.0-SNAPSHOT
	```
2.  更新子模块和父模块一样的版本号;
	```shell
	mvn -N versions:update-child-modules
	```
3.  提交更新
	```shell
	mvn versions:commit
	```

#####  发布版本流程

1. 修改 parent version 版本号；

2. 参考修改版本号流程，修改 version 版本号；

3. 修改 properties 中的  commons.version 版本号； 

4. 执行命令
	```shell
	mvn clean deploy -Possrh
	```

#####  根据Tag创建新分支

1. 获取最新代码，执行下面命令，或其他均可；
	git pull

2. 根据tag创建新的分支
	git branch <new-branch-name> <tag-name>
	
3. 切换到新的分支
	git checkout <new-branch-name>
	
	
4. 把本地创建的分支提交到远程仓库
	git push origin <new-branch-name>
