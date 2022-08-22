

### 功能描述: 

自定义gradle插件 并 将插件发布到本地maven仓库中, 然后在其他项目中使用此插件  

<br/>

<br/>

### 测试环境:

```shell
# gradle/wrapper/gradle-wrapper.properties 中配置gradle的版本为 7.5.1, 如下:
# distributionUrl=https\://services.gradle.org/distributions/gradle-7.5.1-all.zip

$ gradlew --version

------------------------------------------------------------
Gradle 7.5.1
------------------------------------------------------------

Build time:   2022-08-05 21:17:56 UTC
Revision:     d1daa0cbf1a0103000b71484e1dbfe096e095918

Kotlin:       1.6.21
Groovy:       3.0.10
Ant:          Apache Ant(TM) version 1.10.11 compiled on July 10 2021
JVM:          11.0.1 (Oracle Corporation 11.0.1+13)
OS:           Mac OS X 10.16 x86_64
```





<br/>

<br/>

### 操作步骤

```java
// 操作步骤: 
// 1. 先注释掉下面的 "apply ..." 和 "ext.greetingFile = ..." 和 上面的 "classpath ...", 
// 2. 如果没有 gradlew 或 gradlew.bat, 先执行 "gradle wrapper" 命令生成 这两个文件 
//    tips: 执行 "gradle wrapper" 会生成 gradlew,gradlew.bat, gradle目录及此目录下的文件        
// 3. 执行: ./gradlew :myplugin:publish  => 编译插件 并 发布到本地仓库 "..../demo1/repo/" 目录下
// 4. 放开  "apply ..." 和 "ext.greetingFile = ..." 和 上面的 "classpath ..." 行首的注释, 
// 5. 执行: ./gradlew :myplugin-test:generateFile  => 输出如下:
// Hello World !
```



<br/><br/>

```shell
# https://blog.csdn.net/John_desheng/article/details/112663991


# 添加一个远程仓库
$ git remote add origin https://github.com/phoenix19900219/custom-gradle-plugin.git

# 查看远程仓库
$ git remote
origin

$ git remote -v # 查看远程仓库详细信息 (可以看到远程仓库的地址)
origin	https://github.com/phoenix19900219/custom-gradle-plugin.git (fetch)
origin	https://github.com/phoenix19900219/custom-gradle-plugin.git (push)


$ git pull	# 拉取远程仓库内容

# 将本地仓库的分支与远程仓库的分支进行关联
$ git branch --set-upstream-to=origin/main master # 关联分支

$ git push origin  
$ git push origin HEAD:main # 本地分支与远程分支的 分支名不匹配时 !


# -- 推送到远程仓库
$ git push origin local_branch:remote_branch
# 如: 
$ git push origin master:master


# -- 设置关联 (推送一个分支到远程仓库并设置关联)
$ git push --set-upstream origin new_branch
# 如: 推送本地当前分支到 origin 远程仓库 并 与它的master关联
$ git push --set-upstream origin master




$ git status		# git status 可以查看到当前分装, 已经其所关联的远程分支信息!!
On branch master																		# 当前分装信息
Your branch is ahead of 'origin/main' by 1 commit.	# 关联分支信息
  (use "git push" to publish your local commits)

nothing to commit, working tree clean



$ git branch --unset-upstream	# 取消当前分支与远程分支的关联
$ git branch --unset-upstream <local_branch>	# 取消本地某个分支与远程分支的关联



$ git branch -r		# -r 查看远程分支
  origin/main
  origin/master
$ git branch -a		# 查看所有分支 (本地/远程)
  main
* master
  remotes/origin/main
  remotes/origin/master
  
# git merge <some_branch>		# 合并当前分支 与 指定的某个分支
# 如:
$ git merge main		# 将当前分支与 main 分支合并!

```

