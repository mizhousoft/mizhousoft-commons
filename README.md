# beluga-commons

所有服务依赖的工具类。


#  只更新父模块的版本号
mvn versions:set -DnewVersion=1.0.2-SNAPSHOT

# 更新子模块和父模块一样的版本号
mvn -N versions:update-child-modules 

# 提交更新
mvn versions:commit
