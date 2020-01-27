package org.datastructure.LinkedList.SingleLinkedList;

/**
 * @author LinYongJin
 * @date 2020/1/27 14:55
 */
public class Country {

    private int no;

    private String countryName;

    private String nickname;

    public Country next;

    public Country(int no, String countryName, String nickname) {
        this.no = no;
        this.countryName = countryName;
        this.nickname = nickname;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Country{" +
                "no=" + no +
                ", countryName='" + countryName + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
