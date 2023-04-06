### 🎋 分支规范

代码仓库分为以下六类分支：

1. main 分支：线上在跑的版本
   1. 提供给用户使用的**正式版本**和**稳定版本**；
   2. 🏷️ 所有**版本发布**和 **Tag** 操作都在这里进行；
   3. ❌ **不允许**开发者日常 push，只允许从 release 合并。
2. release 分支：将要上线的版本
   1. 从 develop 分支检出，只用于发布前的确认；
   2. 允许从中分出 fix 分支，修复的 commit 需要 push 回 dev；
   3. ❌ **不允许**开发者日常 push，只允许从 dev 合并。
3. dev 分支：日常开发汇总
   1. 开发者可以检出 feature 和 fix 分支，开发完成后 push 回 dev；
   2. 保证领先于 main；
   3. ❌ **不允许**开发者日常 push，只允许完成功能开发或 bug 修复后通过 pull request 进行合并。
4. feature 分支
   1. 从 dev 分支检出，用于新功能开发；
   2. 命名为 `feature/name`，如 `feature/resume_generation`；
   3. 开发完毕，经过测试后合并到 dev 分支；
   4. ✅ 允许开发者日常 push.
5. fix 分支
   1. 从 dev 或 release 分支检出，用于 bug 修复（feature 过程中的 bug 直接就地解决）；
   2. **特殊情况下**允许直接从 main 直接开 fix 分支进行修复；
   3. 命名为 `fix/issue_id`，如 `fix/2` ;
   4. 修复完毕，经过测试后合并到原来的分支（dev/release/main），**并且保证同时合并到 dev**;
   5. ✅ 允许开发者日常 push.
6. chore 分支
   1. 从 dev 分支检出，用于各项修正，如重构、风格优化等；
   2. 命名为 `chore/name`，如 `chore/resume_generation`；
   3. 开发完毕，经过测试后合并到 dev 分支；
   4. ✅ 允许开发者日常 push.

另外，单元测试代码管理后续交流叭

### ⚙ 提交规范

#### Commit Message

```
<type>(<scope>): <subject>

<body>
```

- type 有下面几类
  - `feat` 新功能
  - `fix` 修补bug（在 `<body>` 里面加对应的 Issue ID）
  - `test` 测试相关
  - `style` 代码风格变化（不影响运行）
  - `refactor` 重构（没有新增功能或修复 BUG）
  - `perf` 性能优化
  - `chore` 构建过程变动（包括构建工具/CI等）
- scope（可选）：影响的模块
- subject：主题（一句话简要描述）
- body（可选）：详细描述，包括相关的 issue、bug 以及具体变动等，可以有多行

#### 例子

```
[feat](账号模块): 增加微信登录验证
[fix](管理员 UI): Safari 下界面适配

1. xxx 元素 yyyy
2. aaa 页面 bbbb

Issue: #3
[refactor](招聘信息接口): xxx 接口更新
```



```
[style]: 格式规范更改，重新格式化
```

**请不要用 `fix #3` 之类的 message 关闭 Issue！请按照 BUG 提出与修复的描述用 Pull Request！**
