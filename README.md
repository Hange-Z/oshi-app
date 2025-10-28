# OSHI 系统监控应用

基于 Spring Boot 和 OSHI 库开发的系统监控 Web 应用，提供实时的系统性能指标监控。

## 🚀 功能特性

- ✅ **CPU 监控** - 实时获取 CPU 负载信息
- ✅ **内存监控** - 系统内存使用情况
- ✅ **磁盘监控** - 磁盘空间和使用率
- ✅ **系统信息** - 操作系统和硬件信息
- ✅ **RESTful API** - 提供标准的 REST 接口
- ✅ **跨域支持** - 支持前后端分离部署

## 🛠️ 技术栈

- **后端框架**: Spring Boot 3.5.5
- **系统监控**: OSHI 6.6.5
- **构建工具**: Maven
- **Java 版本**: 17+
- **Web 服务器**: Tomcat (嵌入式)

## 📦 项目结构

```
oshi-app/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/atguigu/oshiapp/
│       │       ├── OshiAppApplication.java    # 应用启动类
│       │       ├── controller/                # 控制器层
│       │       │   └── CpuLoadRestController.java
│       │       ├── service/                   # 服务层
│       │       │   └── CpuLoadMetricsService.java
│       │       └── common/                    # 通用组件
│       │           └── R.java                 # 统一响应格式
│       └── resources/
│           └── application.yml                # 应用配置
├── pom.xml                                    # Maven 配置
└── README.md                                  # 项目说明
```

## 🏃‍♂️ 快速开始

### 环境要求

- JDK 17 或更高版本
- Maven 3.6+
- Windows/Linux/macOS 系统

### 运行步骤

1. **克隆项目**
   ```bash
   git clone https://github.com/Hange-Z/oshi-app.git
   cd oshi-app
   ```

2. **编译运行**
   ```bash
   # 使用 Maven 运行
   mvn spring-boot:run
   
   # 或者先编译再运行
   mvn clean package
   java -jar target/oshi-app-0.0.1-SNAPSHOT.jar
   ```

3. **访问应用**
   ```
   http://localhost:5173
   ```

### 配置文件

在 `src/main/resources/application.yml` 中配置应用参数：

```yaml
server:
  port: 5173
  servlet:
    context-path: /

spring:
  application:
    name: oshi-app
```

## 📡 API 接口文档

### CPU 负载监控

**接口地址**: `GET /metrics/cpuload`

**功能描述**: 获取系统 CPU 负载信息

**响应示例**:
```json
{
  "code": 200,
  "message": "成功",
  "data": [0.25, 0.18, 0.32, 0.41]
}
```

**响应参数**:
- `code`: 状态码 (200=成功)
- `message`: 响应消息
- `data`: CPU 负载数组，每个元素代表一个核心的负载率 (0.0-1.0)

### 更多接口（待开发）

- `GET /metrics/memory` - 内存使用信息
- `GET /metrics/disk` - 磁盘空间信息
- `GET /metrics/system` - 系统基本信息

## 🔧 开发指南

### 添加新的监控指标

1. **在 Service 层创建业务逻辑**
   ```java
   @Service
   public class MemoryMetricsService {
       public MemoryInfo getMemoryInfo() {
           // 实现内存信息获取逻辑
       }
   }
   ```

2. **在 Controller 层添加接口**
   ```java
   @RestController
   @RequestMapping("/metrics")
   public class MemoryRestController {
       
       @Autowired
       private MemoryMetricsService memoryService;
       
       @GetMapping("/memory")
       public R getMemoryInfo() {
           MemoryInfo memoryInfo = memoryService.getMemoryInfo();
           return R.ok(memoryInfo);
       }
   }
   ```

### 自定义配置

修改端口号：
```yaml
server:
  port: 9090  # 修改为其他端口
```

启用调试模式：
```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--debug
```

## 🐛 常见问题

### Q: 端口 5173 被占用怎么办？
A: 修改 `application.yml` 中的端口配置，或使用命令：
```bash
mvn spring-boot:run -D"spring-boot.run.arguments=--server.port=8082"
```

### Q: 如何打包为可执行 JAR？
A: 使用 Maven 打包：
```bash
mvn clean package
java -jar target/oshi-app-0.0.1-SNAPSHOT.jar
```

### Q: 跨域请求失败？
A: 项目已配置 `@CrossOrigin` 注解支持跨域，确保前端地址在允许范围内。

## 📄 许可证

本项目采用 MIT 许可证。

## 🤝 贡献指南

欢迎提交 Issue 和 Pull Request！

1. Fork 本项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 创建 Pull Request

## 📞 联系信息

- 项目地址: [https://github.com/Hange-Z/oshi-app](https://github.com/Hange-Z/oshi-app)
- 问题反馈: [GitHub Issues](https://github.com/Hange-Z/oshi-app/issues)

---

**开始监控你的系统性能吧！** 🎉
