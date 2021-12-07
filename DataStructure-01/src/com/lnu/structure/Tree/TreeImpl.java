package com.lnu.structure.Tree;

import com.lnu.structure.Queue.QueueByArray;

import java.util.EmptyStackException;

public class TreeImpl<T extends Comparable> implements Tree<T>{

    //根结点
    protected BinaryNode<T> root;

    public TreeImpl() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    /**
      * @Description: todo 计算大小
      * @Date: 2021/12/6 15:20
      * @return: int
      * @Version: 1.0
      **/
    @Override
    public int size() {
        return size(root);
    }

   /**
     * @Description: todo 递归实现：定义根节点root后，再用subtree实现递归
     * @Date: 2021/12/6 15:21
     * @Param subtree:
     * @return: int
     * @Version: 1.0
     **/
   private int size(BinaryNode<T> subtree) {
       if (subtree == null)
           return 0;
       else {
           //对比汉诺塔:H(n)=H(n-1) + 1 + H(n-1)
           return size(subtree.left) + 1 + size(subtree.right);
       }
   }

    /**
      * @Description: todo 计算深度
      * @Date: 2021/12/6 14:27
      * @return: int
      * @Version: 1.0
      **/
    @Override
    public int height() {
        return height(root);
    }
    /**
      * @Description: todo 递归实现 计算深度
      * @Date: 2021/12/6 14:25
      * @Param subtree:
      * @return: int
      * @Version: 1.0
      **/
    private int height(BinaryNode<T> subtree){
        if (subtree==null){
            return 0;
        }else {
            int l=height(subtree.left);
            int r=height(subtree.right);
            return (l>r) ? (l+1):(r+1);//返回并加上当前层
        }
    }


    /**
     *@Description : TODO 先根遍历
     *@Date :  16:56
     *@Version : 1.0
     **/
    @Override
    public String preOrder() {
        String sb=preOrder(root);
        if(sb.length()>0){
            //去掉尾部","号
            sb=sb.substring(0,sb.length()-1);
        }

        return sb;
    }
    /**
      * @Description: todo 先根遍历
      * @Date: 2021/12/6 16:50
      * @Param subtree: 根节点
      * @return: java.lang.String
      * @Version: 1.0
      **/
    private String preOrder(BinaryNode<T> subtree){
        StringBuffer sb=new StringBuffer();
        if (subtree!=null) {//递归结束条件
            //先访问根结点
            sb.append(subtree.data+",");
            //遍历左子树
            sb.append(preOrder(subtree.left));
            //遍历右子树
            sb.append(preOrder(subtree.right));
        }
        return sb.toString();
    }

    /**
     *@Description : TODO 中根遍历
     *@Date :  16:56
     *@Version : 1.0
     **/
    @Override
    public String inOrder() {
        String sb=inOrder(root);
        if(sb.length()>0){
            //去掉尾部","号
            sb=sb.substring(0,sb.length()-1);
        }

        return sb;
    }

    /**
      * @Description: todo 中根遍历方法
      * @Date: 2021/12/6 16:57
      * @Param subtree:
      * @return: java.lang.String
      * @Version: 1.0
      **/
    public String inOrder(BinaryNode<T> subtree) {
        StringBuffer sb=new StringBuffer();
        if (subtree!=null) {//递归结束条件
            //先遍历左子树
            sb.append(inOrder(subtree.left));
            //再遍历根结点
            sb.append(subtree.data+",");
            //最后遍历右子树
            sb.append(inOrder(subtree.right));
        }
        return sb.toString();
    }

    /**
     *@Description : TODO 后跟遍历
     *@Date : 2021/12/6 16:58
     *@Version : 1.0
     **/
    @Override
    public String postOrder() {
        String sb=postOrder(root);
        if(sb.length()>0){
            //去掉尾部","号
            sb=sb.substring(0,sb.length()-1);
        }

        return sb;
    }
    /**
      * @Description: todo 后跟遍历方法
      * @Date: 2021/12/6 16:59
      * @Param subtree:
      * @return: java.lang.String
      * @Version: 1.0
      **/
    public String postOrder(BinaryNode<T> subtree) {
        StringBuffer sb=new StringBuffer();
        if (subtree!=null) {//递归结束条件
            //先遍历左子树
            sb.append(postOrder(subtree.left));

            //再遍历右子树
            sb.append(postOrder(subtree.right));

            //最后遍历根结点
            sb.append(subtree.data+",");
        }
        return sb.toString();
    }

    /**
      * @Description: todo 层序遍历 借助队列 （每抛出一个就加入他的孩子）
      * @Date: 2021/12/6 19:27
      * @return: java.lang.String
      * @Version: 1.0
      **/
    @Override
    public String levelOrder() {
        QueueByArray<BinaryNode<T>> queue =new QueueByArray<>();
        StringBuffer sb=new StringBuffer();
        BinaryNode<T> p=this.root;

        while (p!=null){
            //记录经过的结点
            sb.append(p.data);

            //先按层次遍历结点,左结点一定在右结点之前访问
            if(p.left!=null){
                //孩子结点入队
                queue.add(p.left);
            }

            if (p.right!=null){
                queue.add(p.right);
            }
            //访问下一个结点
            p=queue.poll();
        }
        return sb.toString();

    }

    @Override
    public void insert(T data) {
        //检查 如果data为空 抛出异常
        if (data==null){
            throw new RuntimeException("data can't be null!");
        }

        //添加操作
        root = insert(data, root);

    }

    /**
      * @Description: todo 添加新的元素 递归实现
      * @Date: 2021/12/5 20:52
      * @Param data: 添加元素
      * @Param p: 根节点
      * @return: com.lnu.structure.Tree.BinaryNode<T>
      * @Version: 1.0
      **/

    private BinaryNode<T> insert(T data,BinaryNode<T> p){
        //如果根节点为空
        if (p==null){
            p=new BinaryNode<>(null,null,data);
        }

        //比较插入节点值，决定向左子树还是右子树搜索
        int compareResult=data.compareTo(p.data);

        if (compareResult<0){//左
            p.left=insert(data,p.left);
        }else if (compareResult>0){//右
            p.right=insert(data,p.right);
        }else {
            //已有元素没必要重新插入
            System.out.println("this data is head");
        }
        return p;
    }

    @Override
    public void remove(T data) {
        if (data==null){
            throw new RuntimeException("data can't be null!");
        }
        //执行私有删除方法
        root=remove(data , root);
    }

    /**
      * @Description: todo 分3种情况
      * 1.删除叶子结点(也就是没有孩子结点)
      * 2.删除拥有一个孩子结点的结点(可能是左孩子也可能是右孩子)
      * 3.删除拥有两个孩子结点的结点
      * @Date: 2021/12/5 21:04
      * @Param data:
      * @Param p:
      * @return: com.lnu.structure.Tree.BinaryNode<T>
      * @Version: 1.0
      **/
    private BinaryNode<T> remove(T data , BinaryNode<T> p){
        //如果没有找到data元素 递归结束
        if (p==null){
            return p;
        }
        int compareResult=data.compareTo(p.data);

        if (compareResult<0){//向左查找删除节点
            p.left=remove(data,p.left);
        }else if (compareResult>0){//向右查找删除节点
            p.right=remove(data,p.right);
        }else if (p.left!=null && p.right!=null){//已找到结点 并且版判断是否有两个子节点(情况3)
            //中继替换，找到右子叶中最小的元素并替换需要删除的元素值
            p.data = findMin(p.right).data;
            //移除用于替换的节点
            p.right = remove( p.data , p.right);
        }else {
            //拥有一个孩子节点的结点和叶子情况
            p=(p.left!=null)? p.left : p.right;
        }
        //返回该节点
        return p;
    }

    @Override
    public T findMin() {
        if(isEmpty()){
            throw new RuntimeException("BinarySearchTree is empty!");
        }
        return findMin(root).data;
    }
    /**
      * @Description: todo 查找最小值
      * @Date: 2021/12/5 21:09
      * @Param p:
      * @return: com.lnu.structure.Tree.BinaryNode<T>
      * @Version: 1.0
      **/
    private BinaryNode<T> findMin(BinaryNode<T> p){

        if (p==null)//结束条件
            return null;
        else if (p.left==null)//如果没有左结点,那么t就是最小的
            return p;
        return findMin(p.left);
    }


    @Override
    public T findMax() {
        if(isEmpty()){
            throw new RuntimeException("BinarySearchTree is empty!");
        }
        return findMax(root).data;
    }

    /**
      * @Description: todo 查询最大值
      * @Date: 2021/12/5 21:12
      * @Param p:
      * @return: com.lnu.structure.Tree.BinaryNode<T>
      * @Version: 1.0
      **/
    private BinaryNode<T> findMax(BinaryNode<T> p){
        if (p==null)//结束条件
            return null;
        else if (p.right==null)
            return p;
        return findMax(p.right);
    }

    @Override
    public BinaryNode findNode(T data) {
        return null;
    }

    @Override
    public boolean contains(T data) throws Exception {
        return false;
    }

    @Override
    public void clear() {

    }
}
