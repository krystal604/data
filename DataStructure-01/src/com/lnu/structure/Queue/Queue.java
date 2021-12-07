package com.lnu.structure.Queue;

public interface Queue<T> {

    /**
      * @Description: todo 计算队列长度
      * @Date: 2021/12/2 18:35
      * @return: int
      * @Version: 1.0
      **/
    int size();
    /**
      * @Description: todo判断队列是否为空
      * @Date: 2021/12/2 18:36

      * @return: boolean
      * @Version: 1.0
      **/
    boolean isEmpty();

    /**
      * @Description: todo 入队 可扩容
      * @Date: 2021/12/2 18:37
      * @Param data:
      * @return: boolean
      * @Version: 1.0
      **/
    boolean add(T data);

    /**
     * offer 方法可插入一个元素,这与add 方法不同，
     * 该方法只能通过抛出未经检查的异常使添加元素失败。
     * 而不是出现异常的情况，例如在容量固定（有界）的队列中
     * NullPointerException:data==null时抛出
     * @param data
     * @return
     */
    boolean offer(T data);

    /**
     * 返回队头元素,不执行删除操作,若队列为空,返回null
     * @return
     */
    T peek();

    /**
     * 返回队头元素,不执行删除操作,若队列为空,抛出异常:NoSuchElementException
     * @return
     */
    T element();

    /**
     * 出队,执行删除操作,返回队头元素,若队列为空,返回null
     * @return
     */
    T poll();

    /**
     * 出队,执行删除操作,若队列为空,抛出异常:NoSuchElementException
     * @return
     */
    T remove();

    /**
     * 清空队列
     */
    void clearQueue();

}
