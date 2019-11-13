package com.sunflower.goku.fastjson.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author fuyongde
 * @date 2019/11/12 21:42
 */
@Data
public class RBTree<T extends Comparable<T>> implements Serializable {

    /**
     * 根节点
     **/
    private RBNode<T> root;

    @Data
    class RBNode<T extends Comparable<T>> implements Serializable {
        boolean isRed;
        T value;
        RBNode<T> left;
        RBNode<T> right;
        @JSONField(serialize = false)
        RBNode<T> parent;

        public RBNode(boolean isRed, T value, RBNode<T> left, RBNode<T> right, RBNode<T> parent) {
            this.isRed = isRed;
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    /*
     * 左旋示意图：对节点x进行左旋
     *     p                       p
     *    /                       /
     *   x                       y
     *  / \                     / \
     * lx  y      ----->       x  ry
     *    / \                 / \
     *   ly ry               lx ly
     *
     * 左旋做了三件事：
     * 1、将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
     * 2、将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     * 3、将y的左子节点设为x，将x的父节点设为y
     */
    private void leftRotate(RBNode<T> x) {
        //1、将y的左子节点赋给x的右子节点，并将x赋给y左子节点的父节点(y左子节点非空时)
        RBNode<T> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        //2、将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
        y.parent = x.parent;
        if (x.parent == null) {
            //如果x的父节点为空(即x为根节点)，则将y设为根节点
            this.root = y;
        } else {
            //如果x是左子节点
            if (x == x.parent.left) {
                //则也将y设为左子节点
                x.parent.left = y;
            } else {
                //否则将y设为右子节点
                x.parent.right = y;
            }
        }
        //3、将y的左子节点设为x，将x的父节点设为y
        y.left = x;
        x.parent = y;
    }

    /*
     * 右旋示意图：对节点y进行右旋
     *        p                   p
     *       /                   /
     *      y                   x
     *     / \                 / \
     *    x  ry   ----->      lx  y
     *   / \                     / \
     * lx  rx                   rx ry
     * 右旋做了三件事：
     * 1、将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
     * 2、将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
     * 3、将x的右子节点设为y，将y的父节点设为x
     */
    private void rightRotate(RBNode<T> y) {
        //1、将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
        RBNode<T> x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        //2、将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
        x.parent = y.parent;
        if (y.parent == null) {
            this.root = x;
        } else {
            // 如果y结点是右节点
            if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        }
        //3、将x的右子节点设为y，将y的父节点设为x
        x.right = y;
        y.parent = x;
    }

    public void insert(T value) {
        RBNode<T> node = new RBNode<>(true, value, null, null, null);
        System.out.println("插入节点：" + value);
        insert(node);
    }

    private void insert(RBNode<T> node) {
        // 表示最后node的父节点
        RBNode<T> current = null;
        // x 节点是用来向下遍历
        RBNode<T> x = this.root;
        //1、找到插入位置
        while (x != null) {
            current = x;
            int cmp = node.value.compareTo(x.value);
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        // 遍历到 x为空的情况下，说明找到插入的位置，将current做为node的父节点
        node.parent = current;
        //2、接下来判断node是左子节点还是右子节点
        if (current != null) {
            int cmp = node.value.compareTo(current.value);
            if (cmp < 0) {
                current.left = node;
            } else {
                current.right = node;
            }
        } else {
            this.root = node;
        }

        //3、利用旋转操作将其修正为一颗红黑树
        insertFixUp(node);
    }

    private void insertFixUp(RBNode<T> node) {
        //定义父节点和祖父节点
        RBNode<T> parent, grandParent;

        //需要修正的条件：父节点存在，且父节点的颜色是红色
        while (((parent = parentOf(node)) != null) && isRed(parent)) {
            //获得祖父节点
            grandParent = parentOf(parent);

            //若父节点是祖父节点的左子节点，下面的else相反
            if (parent == grandParent.left) {
                //获得叔叔节点
                RBNode<T> uncle = grandParent.right;

                //case1:叔叔节点也是红色
                if (isRed(uncle)) {
                    //把父节点和叔叔节点涂黑
                    setBlack(parent);
                    setBlack(grandParent);
                    //把祖父节点涂红
                    setRed(grandParent);
                    //把位置放到祖父节点处
                    node = grandParent;
                    //继续while循环，重新判断
                    continue;
                }

                //case2:叔叔节点是黑色，且当前节点是右子节点
                if (node == parent.right) {
                    //从父节点出左旋
                    leftRotate(parent);
                    //然后将父节点和自己调换一下，为下面右旋做准备
                    RBNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }

                //case3:叔叔节点是黑色，且当前节点是左子节点
                setBlack(parent);
                setRed(grandParent);
                rightRotate(grandParent);
            } else {
                //若父节点是祖父节点的右子节点，与上面的情况完全相反，本质是一样的
                RBNode<T> uncle = grandParent.left;

                //case1:叔叔节点也是红色的
                if (isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(grandParent);
                    node = grandParent;
                    continue;
                }

                //case2:叔叔节点是黑色的，且当前节点是左子节点
                if (node == parent.left) {
                    rightRotate(parent);
                    RBNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }

                //case3:叔叔节点是黑色的，且当前节点是右子节点
                setBlack(parent);
                setRed(grandParent);
                leftRotate(grandParent);
            }
        }
        //将根节点设置为黑色
        setBlack(root);
    }

    private RBNode<T> parentOf(RBNode<T> node) {
        return node != null ? node.parent : null;
    }

    private boolean isRed(RBNode<T> node) {
        return node != null && node.isRed;
    }

    private boolean isBlack(RBNode<T> node) {
        return !isRed(node);
    }

    private void setRed(RBNode<T> node) {
        if (node != null) {
            node.isRed = true;
        }
    }

    private void setBlack(RBNode<T> node) {
        if (node != null) {
            node.isRed = false;
        }
    }

    private void setRoot(T value) {
        this.root = new RBNode<>(false, value, null, null, null);
    }

    public static void main(String[] args) {
        RBTree<Integer> tree = new RBTree<>();
        for (int i = 0; i < 15; i++) {
            tree.insert(i);
        }
        System.out.println(tree);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
