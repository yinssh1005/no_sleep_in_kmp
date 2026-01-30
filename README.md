# No Sleep In KMP

这是一个使用 Kotlin Multiplatform (Compose Multiplatform) 开发的桌面应用，旨在通过模拟鼠标或键盘操作防止电脑进入休眠。

## 项目结构

*   `composeApp/src` 包含应用程序的源代码。
    *   `commonMain`: 所有平台共用的代码。
    *   `jvmMain`: Desktop (JVM) 平台特有的代码。

## Windows 用户编译构建指南

为了在 Windows 上顺利编译、运行和打包此项目，请参考以下步骤：

### 1. 环境准备

*   **JDK (Java Development Kit)**: 请确保您的电脑上安装了 **JDK 17** 或更高版本。
    *   您可以从 [Adoptium](https://adoptium.net/zh-CN/temurin/releases/?version=17) 下载并安装。
    *   安装后，打开命令提示符 (CMD) 或 PowerShell，输入 `java -version` 检查是否安装成功。
*   **代码拉取**: 使用 Git 克隆本项目到本地。

### 2. 运行应用 (开发模式)

在项目根目录下，使用 Gradle 包装器脚本运行应用：

```shell
.\gradlew.bat :composeApp:run
```
第一次运行时，Gradle 会自动下载必要的依赖，请耐心等待。

### 3. 构建安装包 (打包发布)

本项目支持构建 Windows 原生安装包 (`.msi`)。

**注意**: 打包 MSI 需要您的电脑安装了 [WiX Toolset](https://wixtoolset.org/releases/)。

运行以下命令进行打包：

```shell
.\gradlew.bat :composeApp:packageMsi
```

*   **输出目录**: 打包完成后，您可以在以下路径找到安装包：
    `composeApp\build\compose\binaries\main\msi\`
*   **直接运行可执行文件**: 如果您只想生成不带安装程序的二进制文件，可以尝试：
    ```shell
    .\gradlew.bat :composeApp:createDistributable
    ```
    生成的文件位于 `composeApp\build\compose\binaries\main\app\`。

---

## macOS / Linux 用户

### 运行

```shell
./gradlew :composeApp:run
```

### 打包

*   **macOS (DMG)**: `./gradlew :composeApp:packageDmg`
*   **Linux (DEB)**: `./gradlew :composeApp:packageDeb`

---

更多信息请访问 [Kotlin Multiplatform 官方文档](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)。