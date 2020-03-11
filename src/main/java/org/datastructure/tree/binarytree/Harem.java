package org.datastructure.tree.binarytree;

/**
 * @author LinYongJin
 * @date 2020/3/6 20:08
 */
public class Harem {

    private int id;

    private String name;

    private Harem leftHarem;

    private Harem rightHarem;

    public Harem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (leftHarem != null) {
            leftHarem.preOrder();
        }
        if (rightHarem != null) {
            rightHarem.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void middleOrder() {
        if (leftHarem != null) {
            leftHarem.middleOrder();
        }
        System.out.println(this);
        if (rightHarem != null) {
            rightHarem.middleOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (leftHarem != null) {
            leftHarem.postOrder();
        }
        if (rightHarem != null) {
            rightHarem.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序查找
     */
    public Harem preOrderQuery(int id) {
        if (this.id == id) {
            return this;
        }
        Harem target = null;
        if (leftHarem != null) {
            target = leftHarem.preOrderQuery(id);
        }
        if (target != null) {
            return target;
        }
        if (rightHarem != null) {
            target = rightHarem.preOrderQuery(id);
        }
        return target;
    }

    /**
     * 中序查找
     */
    public Harem middleOrderQuery(int id) {
        Harem target = null;
        if (leftHarem != null) {
            target = leftHarem.middleOrderQuery(id);
        }
        if (target != null) {
            return target;
        }
        if (this.id == id) {
            return this;
        }
        if (rightHarem != null) {
            target = rightHarem.middleOrderQuery(id);
        }
        return target;
    }

    /**
     * 后序查找
     */
    public Harem postOrderQuery(int id) {
        Harem target = null;
        if (leftHarem != null) {
            target = leftHarem.middleOrderQuery(id);
        }
        if (target != null) {
            return target;
        }
        if (rightHarem != null) {
            target = rightHarem.middleOrderQuery(id);
        }
        if (target != null) {
            return target;
        }
        return this.id == id ? this : null;
    }

    /**
     * 删除节点
     *
     * @param id
     */
    public void delete(int id) {
        if (leftHarem != null) {
            if (leftHarem.id == id) {
                this.leftHarem = null;
                return;
            } else {
                leftHarem.delete(id);
            }
        }
        if (rightHarem != null) {
            if (rightHarem.id == id) {
                rightHarem = null;
            } else {
                rightHarem.delete(id);
            }
        }
    }

    /**
     * 顺序存储二叉树(以遍历二叉树的方式遍历数组, 使用前序遍历)
     *
     * @param harems 待遍历的数组
     * @param index 当前数组的索引
     */
    public void orderSaveBinaryTree(Harem[] harems, int index) {
        System.out.println(harems[index]);
        // 遍历左子树(从数组的角度看左子树的根节点的索引是当前索引n的2 * n + 1)
        int leftIndex = 2 * index + 1;
        if (leftIndex < harems.length) {
            orderSaveBinaryTree(harems, leftIndex);
        }
        // 遍历右子树(从数组的角度看左子树的根节点的索引是当前索引n的2 * n + 2)
        int rightIndex = 2 * index + 2;
        if (rightIndex < harems.length) {
            orderSaveBinaryTree(harems, rightIndex);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Harem getLeftHarem() {
        return leftHarem;
    }

    public Harem setLeftHarem(Harem leftHarem) {
        this.leftHarem = leftHarem;
        return this;
    }

    public Harem getRightHarem() {
        return rightHarem;
    }

    public Harem setRightHarem(Harem rightHarem) {
        this.rightHarem = rightHarem;
        return this;
    }

    @Override
    public String toString() {
        return "Harem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
