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
