package com.stone.myplugin

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

class CustomPlugin implements Plugin<Project> {
	@Override 
	void apply(Project project) {
		// 在project对象上创建一个扩展属性, 
		// build.gradle中使用: fileInfo { path 'path/to/file' }
		project.extensions.create("fileInfo", PathExtension) // 注意: project.extensions,  extensions最后有s!!
		project.tasks.create("genFile", CustomPluginTask) {
			// 初始化 CustomPluginTask 的属性 destination
			destination = { project.fileInfo.path }
			doLast {
				println project.file(destination).text
			}

		}
	}
}

class PathExtension {
	String path
}

class CustomPluginTask extends DefaultTask {
	@Internal
	def destination

	File getDestination() {
		// 获取 路径为 destination 的文件对象
		project.file(destination)
	}

	@TaskAction
	def invoke() {
		def file = getDestination()
		file.parentFile.mkdirs()
		file.write("Hello, Nicholas !")
	}
}