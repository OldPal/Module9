package com.goit.dao.hibernate;

import com.goit.dao.DishDao;
import com.goit.model.Category;
import com.goit.model.Dish;
import com.goit.model.Ingredient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class HDishDao implements DishDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void createDish(Dish dish) {
        sessionFactory.getCurrentSession().saveOrUpdate(dish);
    }

    @Transactional
    public Dish findDishById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Dish D WHERE D.id = :id");
        query.setParameter("id", id);
        return (Dish) query.uniqueResult();
    }

    @Transactional
    public Dish findDishByTitle(String dishTitle) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Dish D WHERE D.dishTitle = :dishTitle");
        query.setParameter("dishTitle", dishTitle);
        return (Dish) query.uniqueResult();
    }

    @Transactional
    public List<Dish> getAllDish() {
        return sessionFactory.getCurrentSession().createQuery("select d from Dish d").list();
    }

    @Transactional
    public List<Ingredient> getAllIngredientByDishId(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "FROM Dish as d LEFT JOIN FETCH  d.ingredients WHERE d.id =" + id);
        Dish dish = (Dish) query.uniqueResult();

        return new ArrayList<Ingredient>(dish.getIngredients());
    }

    @Transactional
    public void deleteDish(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Dish WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public void updateDishTitle(int id, String newDishTitle) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Dish set dishTitle = :dishTitle where id = :id");
        query.setParameter("dishTitle", newDishTitle);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public void updateDishCategoryId(int id, Category newCategory) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Dish set category = :category where id = :id");
        query.setParameter("category", newCategory);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public void updateDishPrice(int id, float newDishPrice) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Dish set price = :price where id = :id");
        query.setParameter("price", newDishPrice);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public void updateDishWeight(int id, float newDishWeight) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Dish set weight = :weight where id = :id");
        query.setParameter("weight", newDishWeight);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public void updateDistIngredients(int id, List<Ingredient> newIngredients) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Dish set ingredients = :ingredients where id = :id");
        query.setParameter("ingredients", newIngredients);
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
