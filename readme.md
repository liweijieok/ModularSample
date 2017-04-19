# 使用ARouter实现组件化
ARouter是阿里在github上面的一个开源项目，地址是：[ARouter](https://github.com/alibaba/ARouter/)
本文不是重点分享ARouter的使用，而是分享使用ARouter如何去组件化。关于它的详细使用，大家可以看文档以及加群向询问。关于如何编写一个路由实现组件化，推荐看我同事的一篇文章[手把手教你写Router框架入门篇](http://www.jianshu.com/p/fe436a4ac530)
## 组件化的优点
1. 解耦，使得各自业务模块专注于自己的业务实现，而可以不关系别的模块业务。
2. 方便开发，在多人开发的时候，可以各自开发自己的特定模块，除了底层模块之外
3. 可配置，复用性强，针对不同的App，可以有不同的模块而不必做出大的改变。
4. 每个模块可以独立运行，方便开发调试。
5. 大家补充
## 组件化开发的实现
项目结构是:
![](http://ocxgpwj6l.bkt.clouddn.com/moduleDirectory.png)
### 配置
1. 大家按照ARouter的文档去配置一些东西，需要注意的是每一个模块都必须引入compile sdk，处理API SDk可以在Base sdk里面引入就行。
2. Annotation sdk不用我们引入，他会自己自动依赖引入
3. 在开发的时候，每一个module 的分组都必须不同，或者将会有一个 类重复的错误。分组就是path的第一个"/"与第二个"/"之间。
### 思路
我们是需要拆分组件，不同的模块负责自己的业务实现，不和其他模块有依赖关系的存在。假如这个模块需要启动别的Activity或者是调用别的模块方法，我们就通过ARouter提供的方法去实现。模块图如下：
![](http://ocxgpwj6l.bkt.clouddn.com/module.png)
模块解析与划分规则：
1. App模块
App模块就是我们的Apk模块，他是我们的最终产物。他可以按照需要配置自己需要的模块，进行一些初始化的工作。
2. module模块
Module模块就是我们的业务实现模块，他是只负责本module的业务而不负责其他的业务，他是通过使用ARouter提供的方法和接口，实现调用外部数据和提供方法给外部调用，也就是图里面的Provider。
3. base模块
关于base模块，大家也可以继续拆分一些细的模块，比如把router独立处理，但是不建议拆分太多，因为base模块是会变动较大，就是在版本的迭代过程中，不断变化的，被拆分较多的话开发起来不是很方便。因为有依赖的传递的关系，比如base 依赖于本project的较多模块，当设计到底层较多模块修改，那么就需要一层一层的在传递上去。
base模块应该是与业务无关的模块,在本例子中，base模块负责管理Router，同时提供一些基础的东西，比如BaseActivit，Util资源等。假如我们有自家的sdk，网络库依赖等，建议是在base中引入。
### 实践
#### 管理Router
我们使用一个ModuleManager，提供一个Map，Map是使用对应module的Provider的path作为key和value。以及相关的数据结构，用来对ARouter的进行二次封装和管理模块。
大概实现如下：
```
public class ModuleManager {
    private ModuleOptions options;
    private ModuleManager() {
    }
    private static class ModuleManagerHolder {
        private static final ModuleManager instance = new ModuleManager();
    }
    public static ModuleManager getInstance() {
        return ModuleManagerHolder.instance;
    }
    public void init(ModuleOptions options) {
        if (this.options == null && options != null) {
            this.options = options;
        }
    }
    public ModuleOptions getOptions() {
        return options;
    }
    public boolean hasModule(String key) {
        return options.hasModule(key);
    }
}
```
ModuleOptions就是具体管理那些包含那些模块的配置。该类是在App或者是测试module独立运行(后面提到)的时候进行初始化。例如:
```
public class CustomApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initARouter();
    }
    private void initARouter() {
        if (LG.isDebug) {
            ARouter.openLog();
            ARouter.openDebug();
            ARouter.printStackTrace();
        }
        ARouter.init(this);
        ModuleOptions.ModuleBuilder builder = new ModuleOptions.ModuleBuilder(this)
                .addModule(IHomeProvider.HOME_MAIN_SERVICE, IHomeProvider.HOME_MAIN_SERVICE)
                .addModule(IModule1Provider.MODULE1_MAIN_SERVICE, IModule1Provider.MODULE1_MAIN_SERVICE)
                .addModule(IModule2Provider.MODULE2_MAIN_SERVICE, IModule2Provider.MODULE2_MAIN_SERVICE)
                .addModule(IModule4Provider.MODULE4_MAIN_SERVICE, IModule4Provider.MODULE4_MAIN_SERVICE)
                .addModule(IModule5Provider.MODULE5_MAIN_SERVICE, IModule5Provider.MODULE5_MAIN_SERVICE);
        ModuleManager.getInstance().init(builder.build());
    }
}
```
这样子就完成了对改App或者是module的管理。
#### 管理服务
我们使用一个ServiceManager，用来获取不同模块的服务，即Provider。安装ARouter的文档，我们通过继承IProvider，编写一个对应模块的接口，提供接口方法，在对应模块实现该Provider。然后该Provider我们就是在ServiceManager里面进行管理和获取。比如：
home模块实现一个IHomeProvider，实现类是HomeProvider。
```
//接口
public interface IHomeProvider extends IBaseProvider {
    //Service
    String HOME_MAIN_SERVICE = "/home/main/service";
    //开屏
    String HOME_ACT_SPLASH = "/home/act/splash";
    //home主页
    String HOME_ACT_HOME = "/home/act/home";
    String HOME_TABTYPE = "home_tab_type";
    void toast(String msg);
    void selectedTab(Activity activity,int position);
}
//实现类
@Route(path = IHomeProvider.HOME_MAIN_SERVICE)
public class HomeProvider implements IHomeProvider {
    private Context context;
    @Override
    public void init(Context context) {
        this.context = context;
    }
    @Override
    public void toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void selectedTab(Activity activity,int position) {
        if (activity instanceof HomeActivity) {
            ((HomeActivity) activity).selectedTab(position);
        }
    }
}
```
然后在ServiceManager中，
```
    //也可以使用自动注入，这里是手动发现并且调用
  public IModule1Provider getModule1Provider() {
        return module1Provider != null ? module1Provider : (module1Provider = ((IModule1Provider) MyRouter.newInstance(IModule1Provider.MODULE1_MAIN_SERVICE).navigation()));
    }
```
我们对本Project的所有服务进行管理。然后，在base当中，提供不同的Service，对Provider进行调用，同时提供Intent方法，去启动不同模块的Activity，比如：
```
//管理调用Provider的某一个特定模块的Service
public class HomeService {
    private static boolean hasModule() {
        return ModuleManager.getInstance().hasModule(IHomeProvider.HOME_MAIN_SERVICE);
    }
    public static void selectedTab(Activity activity, int position) {
        if (!hasModule()) return;
        ServiceManager.getInstance().getHomeProvider().selectedTab(activity, position);
    }
}
//管理该module的Activity跳转
public class HomeIntent {
    private static boolean hasModule() {
        return ModuleManager.getInstance().hasModule(IHomeProvider.HOME_MAIN_SERVICE);
    }
    public static void launchHome(int tabType) {
        //HomeActivity
        MyBundle bundle = new MyBundle();
        bundle.put(IHomeProvider.HOME_TABTYPE, tabType);
        MyRouter.newInstance(IHomeProvider.HOME_ACT_HOME)
                .withBundle(bundle)
                .navigation();
    }
}
```
#### Module可独立运行配置
经过这两个，我们就已经基本完成了项目组件化。但是对于组件化，我们还有一个特点，就是每一个module都是可以独立运行的，方便开发和调试。那么，我们应该怎么弄？
1. 我们提供两种环境，一个是debug，一种是release，debug的时候，我们是可运行的独立模块，release的时候，我们是library。
2. debug的时候，我们需要提供一些测试代码和一套初始化。
例如:我们需要module1是可以独立运行的，在本demo中，他是一个Fragment作为主入口被别的模块添加使用，所以我们的debug中需要添加一个Activity，一套清单，一些资源。
我们使用config.gradle去管理我们的一些外部依赖arr以及我们的一些编译版本号，sdk版本号等，如下：
```
ext {
       //...版本号以及arr管理
    //home是否是作为模块，true的时候是，false的时候可以独立运行，ps名字有点不对，不想改了ORZ
    isMouleDebugHome = true;
    //module1是否是作为模块，true的时候是，false的时候可以独立运行
    isModule1Debug = true;
}
```
然后，在跟build.gradle第一行中apply 进去。
```
apply from: "config.gradle"
```
然后，使用sourceSets对代码进行管理，配置debug和release的代码，module1的结构如下
![](http://ocxgpwj6l.bkt.clouddn.com/module1.png)
他的gradle配置如下：
```
if (rootProject.ext.isModule1Debug) {
    apply plugin: 'com.android.library'
} else {
    apply plugin: 'com.android.application'
}
android {
    compileSdkVersion rootProject.ext.android.compileSdkVersion
    buildToolsVersion rootProject.ext.android.buildToolsVersion
    defaultConfig {
        minSdkVersion rootProject.ext.android.minSdkVersion
        targetSdkVersion rootProject.ext.android.targetSdkVersion
        versionCode 101
        versionName "1.0.1"
        if (!rootProject.ext.isModule1Debug) {
            applicationId "com.github.io.liweijie.lib1"
        }
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = [moduleName: project.getName()]
            }
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_7
        targetCompatibility JavaVersion.VERSION_1_7
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    sourceSets {
        main {
            if (!rootProject.ext.isModule1Debug) {
                manifest.srcFile 'src/debug/AndroidManifest.xml'
                java.srcDir 'src/debug/java/'
                res.srcDirs=['src/debug/res']
            } else {
                manifest.srcFile 'src/release/AndroidManifest.xml'
                java.srcDir 'src/release/java/'
            }
        }
    }
}
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile project(':base')
    testCompile 'junit:junit:4.12'
    annotationProcessor rootProject.ext.dependencies["aroutercompiler"]
}
```
这里需要注意的问题是，debug和relase的清单都需要声明需要的activity以及其他组件。debug中还应该配置Application，进行ARouter的初始化。
经过这样子，我们的每一个module都是可以独立运行的模块了。一般而言，release其实是没有什么东西的，因为release需要的就是我们module本身需要的业务逻辑实现代码，他是作为library去使用的，看自己项目是否需要配置relase。
最终结果如图：
App独立运行：
![](https://github.com/liweijieok/ModularSample/blob/master/art/app1.gif)
App2独立运行：
![](https://github.com/liweijieok/ModularSample/blob/master/art/app2.gif)
home独立运行：
![](https://github.com/liweijieok/ModularSample/blob/master/art/home.gif)
module1独立运行：
![](https://github.com/liweijieok/ModularSample/blob/master/art/module.gif)
## 组件化开发的建议
1. 在我们每一个模块文档之后，我们应该使用arr的形式引入依赖，上传我们的maven库，再去compile下来，同时注释掉setting.gradle的配置，这样子有助于编译加快。
2. 删除各个module的test代码，也就是src目录下的test，因为那些都是包含一些task的，在同步或者是编译的时候会被执行，减慢了编译速度。
3. 四大组件应该在各自module里面声明。
4. 在Base中提供一个BaseApplication。
## 旧项目组件化过程实践
最近在做公司的App，和同事一起把负责的App组件化了，在这个过程中有一些坑跟大家分享一下，避免大家再踩。
1. 不要一步彻底的组件化，步子不要迈得太大，建议是先将比较容易的组件进行拆分出来，先做成模块，然后一边进行业务的迭代，两边都不耽误，除非你有足够时间进行组件化工作才去彻底组件化。
2. 关于资源的拆分，一些style，一些常见的string，一些共用的图片，drawable等资源，建议存放在base module当中，可以共用。对于属于不同模块的资源，建议不要存放在base，可能一开始你直接把所有的资源都放到base里面会比较好做，比如不用担心编译错误，资源无法寻找到，需要一个一个的复制的问题，但是，你存放到了base，而base模块，一般开发过程中，是经常变动的，那么，就是他会经常被编译，就会带来一个编译的速度问题，所以应该各个模块存放自己的资源。
3. 关于Base的依赖库问题，比如我们可能有自家的sdk，第三方的依赖等等，这些，与业务逻辑无关的依赖，建议是使用一个单独的project 进行二次封装，封装完成之后，打包arr 依赖，上传自家maven库，通过compile 进行base，或者是特定模块，不建议直接在app中新建module。比如，我们要引入volley，我们应该使用一个新的project，对volley进行二次封装，稳定之后，通过compile arr 引入base 或者是其他模块中。这样子这些与业务逻辑无关的也方便公司其他项目使用。
4. 针对旧项目拆分的module，可以暂时不编写debug，也就是可以不用使得旧项目可以独立运行，因为他涉及的初始化逻辑可能较多，时间不够，直接被app依赖运行测试。针对新的module，可以编写debug来使得新module独立运行，方便调试。
## 组件化带来的问题
1. 由于项目的组价话，可能需要每个业务拆分的比较开，那么将会导致module也比较的多。到了最后，可能是一个模块带一个Activity以及一个Fragment作为一个组件，那么将会比较难的开发，所以，模块的拆分，我们需要按照我们实际项目进行划分模块，不要划分太细致。同时，在旧项目组件化过程中，由于各个模块尚未成熟，还没有稳定，那么就会有比较多的模块进行编译，速度也是比较慢的，所以旧项目的拆分也是需要按照一定的步骤慢慢拆分。同时，建议使用Activity+Fragment的方式进行组件化，方便被别的组件使用，Activity不涉及一般逻辑。
2. 有时候修改base库，需要修改依赖方式会比较的麻烦。
3. 有时候会有资源冲突问题。

本文如有什么写的错误的，请大家指正，一起进步。


