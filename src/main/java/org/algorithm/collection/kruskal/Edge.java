package org.algorithm.collection.kruskal;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author LinYongJin
 * @date 2020/4/26 16:26
 */
@Data
@AllArgsConstructor
public class Edge {

    private char start;

    private char end;

    private int weight;

}
