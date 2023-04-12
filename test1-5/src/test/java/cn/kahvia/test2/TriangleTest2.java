package cn.kahvia.test2;

import cn.kahvia.test1_2.Triangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TriangleTest2 {
    Triangle triangle=new Triangle();
    @ParameterizedTest
    @DisplayName("普通参数测试")
    @CsvSource({
            "1,2,3,非三角形",
            "-1,2,3,输入错误"
    })
    void testWithCsvSource(int a,int b,int c,String expected) {
        assertEquals(expected, triangle.classify(a,b,c));
    }

    @ParameterizedTest
    @DisplayName("一般边界测试")
    @CsvFileSource(resources = "/test2/triangle_common_boundary.csv")
    void test_csv_file_source_common_boundary(int a, int b, int c, String expected) {
        assertEquals(expected, triangle.classify(a,b,c));
    }

    @ParameterizedTest
    @DisplayName("健壮性边界测试")
    @CsvFileSource(resources = "/test2/triangle_strong_boundary.csv")
    void test_csv_file_source_strong_boundary(int a, int b, int c, String expected) {
        assertEquals(expected, triangle.classify(a,b,c));
    }

    @ParameterizedTest
    @DisplayName("一般最坏测试")
    @CsvFileSource(resources = "/test2/triangle_common_worst.csv")
    void test_csv_file_source_common_worst(int a, int b, int c, String expected) {
        assertEquals(expected, triangle.classify(a,b,c));
    }

    @ParameterizedTest
    @DisplayName("健壮最坏测试")
    @CsvFileSource(resources = "/test2/triangle_strong_worst.csv")
    void test_csv_file_source_strong_worst(int a, int b, int c, String expected) {
        assertEquals(expected, triangle.classify(a,b,c));
    }
}