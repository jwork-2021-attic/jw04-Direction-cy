## 任务一

1. Tile类型表示一种不确定，明确需要放一种东西thing，但是不确定具体是什么类型，用了泛型的设计，表示放的东西是Thing及其子类中的一种

2. Tile<? extends Thing> tile，使用了通配符，表示的是可以装所有Thing及其子类的某种的tile，是Tile<Thing> 以及Tile<xx>(x extends Thing) 的基类

3. Comparable< T > 是一个接口, 泛型参数<T extends Comparable< T >> 限定了边界是Comparable< T >， 意味着类型参数必须支持通过 Comparable 接口进行比较。BubbleSorter 实现 Sorter 接口， <T extends Comparable< T >> 在 Bubble中界定无需重新界定。

