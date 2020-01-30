package org.datastructure.linkedList.doublelinkedList;

/**
 * @author LinYongJin
 * @date 2020/1/30 15:28
 */
public class DoubleLinkedList {

    private Country head;

    private int length;

    /**
     * 添加节点
     * @param country 将要添加的节点
     */
    public void add(Country country) {
        Country temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = country;
        if (length > 0) {
            country.pre = temp;
        }
        length++;
    }

    /**
     * 顺序添加
     * @param country 将要添加的节点
     */
    public void orderAdd(Country country) {
        Country temp = head;
        while (head.next != null) {
            if (country.getNo() > temp.next.getNo()) {
                temp = temp.next;
            } else {
                country.next = temp.next;
                temp.next.pre = country;
                temp.next = country;
                if (head == temp) {
                    country.pre = temp;
                }
                length++;
                return;
            }
        }
        temp.next = country;
        length++;
    }

    /**
     * 修改链表
     * @param country 要修改的链表
     */
    public void update(Country country) {
        if (length == 0) {
            System.out.println("链表为空");
            return;
        }
        Country temp = head;
        while (temp.next != null) {
            if (temp.next.getNo() == country.getNo()) {
                temp.next.setCountryName(country.getCountryName());
                temp.next.setNickname(country.getNickname());
                return;
            }
            temp = temp.next;
        }
    }

    /**
     * 删除链表
     * @param no 指定删除的no
     */
    public void delete(int no) {
        if (length == 0) {
            System.out.println("链表为空");
            return;
        }
        Country temp = head;
        while (temp.next != null) {
            if (temp.next.getNo() == no) {
                temp.next = temp.next.next;
                if (temp == head) {
                    temp.next.pre = null;
                } else {
                    temp.next.pre = temp;
                }
                length--;
                return;
            } else {
                temp = temp.next;
            }
        }
    }

    /**
     * 查询节点
     * @param no
     * @return
     */
    public Country get(int no) {
        if (length == 0) {
            System.out.println("链表为空");
            return null;
        }
        Country temp = head;
        while (temp.next != null) {
            if (temp.next.getNo() == no) {
                return temp.next;
            } else {
                temp = temp.next;
            }
        }
        return null;
    }

    /**
     * 获得倒数的节点
     * @param index
     * @return
     */
    public Country getReciprocal(int index) {
        if (length == 0) {
            System.out.println("链表为空");
            return null;
        }
        if (index == 0 || index > length) {
            System.out.println("请输入合法的索引");
            return null;
        }
        int offset = length - (index - 1);
        Country temp = head;
        for (int i = 0; i < offset; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 反转链表
     */
    public void reverse() {
        Country current = head.next;
        Country next = current.next;
        Country newHead = new Country(0, "", "");
        while (current != null) {
            current.next = newHead.next;
            if (newHead.next != null) {
                current.next.pre = current;
            }
            newHead.next = current;
            current = next;
            if (next != null) {
                next = next.next;
            }
        }
        head.next = newHead.next;
        newHead.next = null;
    }

    /**
     * 合并链表
     * @param doubleLinkedList
     */
    public void addAll(DoubleLinkedList doubleLinkedList) {
        Country temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        length += doubleLinkedList.size();
        temp.next = doubleLinkedList.linkedFirst();
        doubleLinkedList.linkedFirst().pre = temp;
    }

    /**
     * 按顺序合并链表
     * @param doubleLinkedList
     */
    public void orderAddAll(DoubleLinkedList doubleLinkedList) {
        addAll(doubleLinkedList);
        Country current = head.next;
        Country next = current.next;
        Country newHead = new Country(0, "", "");
        Country newTemp = newHead;
        boolean flag = true;
        while (current != null) {
            while (newTemp.next != null) {
                if (current.getNo() < newTemp.next.getNo()) {
                    current.next = newTemp.next;
                    current.next.pre = current;
                    newTemp.next = current;
                    if (newTemp == newHead) {
                        current.pre = null;
                    } else {
                        current.pre = newTemp;
                    }
                    flag = false;
                    break;
                } else {
                    newTemp = newTemp.next;
                }
            }
            if (flag) {
                current.next = null;
                newTemp.next = current;
                if (newTemp == newHead) {
                    current.pre = null;
                } else {
                    current.pre = newTemp;
                }
            }
            newTemp = newHead;
            current = next;
            if (next != null) {
                next = next.next;
            }
            flag = true;
        }
        head.next = newHead.next;
        newHead.next = null;
    }
    
    

    /**
     * 得到第一个节点
     * @return
     */
    public Country linkedFirst() {
        return head.next;
    }

    /**
     * 获取链表长度
     * @return
     */
    public int size() {
        return length;
    }

    @Override
    public String toString() {
        Country temp = head.next;
        StringBuffer sb = new StringBuffer();
        while (temp != null) {
            sb.append(temp).append("\n");
            temp = temp.next;
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }
}
