### 通用服務元件

為提升開發速度，提供常用元件設計開放設計，

### 目前提供功能
* 任務拆分 IGroupTaskService


### CHANGELOG
- 0.0.1  加入任務拆分
- 0.0.2  修改中斷方式，不強制中斷採取






```java


/**
 * 任務拆分服務
 */
public interface IGroupTaskService {

    /**
     * 任務執行
     * @param groupTaskOption
     * @param <T>
     */
    <T> void execute(GroupTaskOption<T> groupTaskOption);



    interface IGroupTask<T> {
        void run(List<T> tasks);
    }
}

void test() {

    GroupTaskService groupTaskService = new GroupTaskService();
    GroupTaskOption.GroupTaskOptionBuilder<Integer> groupTaskOptionBuilder = new GroupTaskOption.GroupTaskOptionBuilder();
    GroupTaskOption<Integer> input = groupTaskOptionBuilder
            .name("test")//
            .threadSize(threadSize)//
            .groupSize(groupSize)//
            .task(run)//
            .tasks(collect)//
            .build();
    groupTaskService.execute(input);
}
```
