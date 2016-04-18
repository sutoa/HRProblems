package org.nextgen.hackerranksolution.hoffmandecoding;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HoffmanDecodingTest {
    private Solution solution = new Solution();
    private Node root;

    @Before
    public void setUp() throws Exception {
        root = buildTree();

    }

    private Node buildTree() {
        Node b = new Node(1, 'B', null, null);
        Node c = new Node(1, 'C', null, null);
        Node b1 = new Node(2, '\0', b, c);
        Node a = new Node(3, 'A', null, null);
        Node root = new Node(5, '\0', b1, a);

        return root;
    }

    @Test
    public void decode() throws Exception {
        assertThat(solution.decodeHoffmanCode("1001011", root)).isEqualTo("ABACA");

    }
}
