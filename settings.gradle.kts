pluginManagement {
    // 版本巡检插件由本机仓库（~/.m2）消费：源码分支改完执行一次 publishToMavenLocal 即可生效，
    // 不再 includeBuild 源码工程，避免每次构建都为它配置并编译插件
    repositories {
        mavenLocal()
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        gradlePluginPortal()
        mavenCentral()
        // 阿里云镜像作为 fallback
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
    // 版本号与 ../refreshVersions/plugins/version.txt 保持一致
    id("de.fayard.refreshVersions") version "1.0.1"
}

refreshVersions {
    // 只接受正式版本：预发布标签与 JetBrains IDE 内部构建号都带连字符，
    // 而插件对无法识别的后缀会兜底判为 Stable，故按连字符特征统一滤除
    rejectVersionIf {
        candidate.value.contains('-')
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        
    }
}

rootProject.name = "Tdds"
include(":app")
