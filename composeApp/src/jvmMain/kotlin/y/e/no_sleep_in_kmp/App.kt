package y.e.no_sleep_in_kmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.awt.MouseInfo
import java.awt.Robot
import java.awt.event.KeyEvent

enum class MouseMode(val label: String) {
    None("不移动"),
    Horizontal("左右移动"),
    Vertical("上下移动")
}

enum class KeyboardMode(val label: String) {
    None("不输入"),
    Key9("按数字9"),
    KeyCtrl("按Ctrl键")
}

@Composable
fun App() {
    MaterialTheme {
        SimulatorTab()
    }
}

@Composable
fun SimulatorTab() {
    var mouseMode by remember { mutableStateOf(MouseMode.None) }
    var keyboardMode by remember { mutableStateOf(KeyboardMode.None) }
    var isRunning by remember { mutableStateOf(false) }

    // 模拟逻辑
        LaunchedEffect(isRunning, mouseMode, keyboardMode) {
            if (isRunning) {
                val robot = try {
                    Robot()
                } catch (e: Exception) {
                    null
                }
                while (isActive) {
                    if (robot != null) {
                        // 执行鼠标移动
                        if (mouseMode != MouseMode.None) {
                            val pos = MouseInfo.getPointerInfo().location
                            when (mouseMode) {
                                MouseMode.Horizontal -> {
                                    robot.mouseMove(pos.x + 10, pos.y)
                                    delay(100)
                                    robot.mouseMove(pos.x, pos.y)
                                }
                                MouseMode.Vertical -> {
                                    robot.mouseMove(pos.x, pos.y + 10)
                                    delay(100)
                                    robot.mouseMove(pos.x, pos.y)
                                }
                                else -> {}
                            }
                        }

                        // 执行键盘输入
                        if (keyboardMode != KeyboardMode.None) {
                            when (keyboardMode) {
                                KeyboardMode.Key9 -> {
                                    robot.keyPress(KeyEvent.VK_9)
                                    robot.keyRelease(KeyEvent.VK_9)
                                }
                                KeyboardMode.KeyCtrl -> {
                                    robot.keyPress(KeyEvent.VK_CONTROL)
                                    robot.keyRelease(KeyEvent.VK_CONTROL)
                                }
                                else -> {}
                            }
                        }
                    }
                    delay(1000) // 每隔1秒执行一次
                }
            }
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "模拟器控制面板",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // 左边：鼠标设置
                    Column(modifier = Modifier.weight(1f)) {
                        Text("鼠标移动方式", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        MouseMode.values().forEach { mode ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .selectable(
                                        selected = (mouseMode == mode),
                                        onClick = { mouseMode = mode },
                                        role = Role.RadioButton
                                    )
                                    .padding(horizontal = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = (mouseMode == mode),
                                    onClick = null // 由 selectable 处理
                                )
                                Text(
                                    text = mode.label,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }

                    // 分隔线
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                            .background(Color.Gray.copy(alpha = 0.5f))
                    )

                    // 右边：键盘设置
                    Column(modifier = Modifier.weight(1f).padding(start = 16.dp)) {
                        Text("键盘输入模拟", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        KeyboardMode.values().forEach { mode ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .selectable(
                                        selected = (keyboardMode == mode),
                                        onClick = { keyboardMode = mode },
                                        role = Role.RadioButton
                                    )
                                    .padding(horizontal = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = (keyboardMode == mode),
                                    onClick = null
                                )
                                Text(
                                    text = mode.label,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { isRunning = !isRunning },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isRunning) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(if (isRunning) "停止运行" else "开始运行", fontSize = 18.sp)
                }
            }
        }
    }