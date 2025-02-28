package ru.netology.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.*;

public class ShopRepositoryTest {

    Product product1 = new Product(23, "Колбаса", 500);
    Product product2 = new Product(2, "Овощи", 300);
    Product product3 = new Product(6, "Вода", 50);
    Product product4 = new Product(6, "Еда", 555);

    @Test
    void shouldAdd() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1, product2, product3);

        Product[] actual = repo.findAll();
        Product[] expected = {product1, product2, product3};

        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    void shouldNotAdd() {
        ShopRepository repo = new ShopRepository();

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product1, product2, product3, product4);
        });
    }

    /**
     * Поскольку я доработал метод add() чтобы он мог принимать несколько параметров,
     * то необходимо проверить, что метод выдаст ошибку на продукт с совпадающим id,
     * но добавит остальные в массив.
     */

    @Test
    void shouldBlockCopyButAddOther() {
        ShopRepository repo = new ShopRepository();
        try {
            repo.add(product1, product2, product3, product4);
        } catch (AlreadyExistsException e) {
            Product[] expected = {product1, product2, product3};
            Product[] actual = repo.findAll();

            Assertions.assertArrayEquals(expected, actual);
        }
    }

    @Test
    void shouldFindById() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1, product2, product3);

        Product actual = repo.findById(23);
        Product expected = product1;

        Assertions.assertEquals(actual, expected);
    }

    @Test
    void shouldNotFindById() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1, product2, product3);

        Product actual = repo.findById(76);
        Product expected = null;

        Assertions.assertEquals(actual, expected);
    }

    @Test
    void shouldRemoveById() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1, product2, product3);
        repo.remove(2);

        Product[] actual = repo.findAll();
        Product[] expected = {product1, product3};

        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    void shouldNotRemoveById() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1, product2, product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(66);
        });
    }
}
