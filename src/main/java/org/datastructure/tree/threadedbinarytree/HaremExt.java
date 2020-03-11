package org.datastructure.tree.threadedbinarytree;

import org.datastructure.tree.binarytree.Harem;

import java.util.StringJoiner;

/**
 * @author LinYongJin
 * @date 2020/3/11 20:54
 */
public class HaremExt {

    private int id;

    private String name;

    private HaremExt leftHarem;

    private HaremExt rightHarem;

    /**
     * 左子节点的类型
     * 0: 默认, 指向指的是孩子节点
     * 1: 指向是前驱节点
     */
    private int leftNodeType;

    /**
     * 右子节点的类型
     * 0: 默认, 指向指的是孩子节点
     * 1: 指向是前驱节点
     */
    private int rightNodeType;

    public HaremExt(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getLeftNodeType() {
        return leftNodeType;
    }

    public HaremExt setLeftNodeType(int leftNodeType) {
        this.leftNodeType = leftNodeType;
        return this;
    }

    public int getRightNodeType() {
        return rightNodeType;
    }

    public HaremExt setRightNodeType(int rightNodeType) {
        this.rightNodeType = rightNodeType;
        return this;
    }

    public int getId() {
        return id;
    }

    public HaremExt setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public HaremExt setName(String name) {
        this.name = name;
        return this;
    }

    public HaremExt getLeftHarem() {
        return leftHarem;
    }

    public HaremExt setLeftHarem(HaremExt leftHarem) {
        this.leftHarem = leftHarem;
        return this;
    }

    public HaremExt getRightHarem() {
        return rightHarem;
    }

    public HaremExt setRightHarem(HaremExt rightHarem) {
        this.rightHarem = rightHarem;
        return this;
    }

}
