package com.stone.myplugin

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project 
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

class CustomPlugin implements Plugin<Project> {

	@Override
	void apply(Project project) {
		project.tasks.create("generateFile", CustomPluginTask) {
			// 给 CustomPluginTask 的属性 destination 赋值
			destination = { project.greetingFile } // 应用此插件的build.gradle中设置project.greetingFile的值,  
													// 通过扩展属性设置, 因为project并没有属性greetingFile, 设置方式如下:
													// ext.greetingFile = ""
													// 或者 extension.greetingFile = ""
			doLast {
				// project.file() 创建一个文件
				// 文件的text属性, 获得文件的内容
				println project.file(destination).text
			}
		}
	}
}


class CustomPluginTask extends DefaultTask {
	// 这个属性, 在插件内部 (创建时就赋值了, 因此需要用 @Internal 注解标注, 否则会报错) 使用,
    // 在CustomPlugin中创建时, 用 project.greetingFile 来进行赋值!
	@Internal
	def destination 

	File getDestination() {
		// 获得路径为 destination 的文件
		project.file(destination)
	}


	// task真正的执行逻辑, 用 @TaskAction 注解标注
	@TaskAction
	def greet() {
		def file = getDestination()
		file.parentFile.mkdirs() // 创建父目录
		// 向文件中写入一些内容: 
		file.write('Hello world !')
	}

}