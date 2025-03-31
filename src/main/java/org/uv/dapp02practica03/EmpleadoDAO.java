package org.uv.dapp02practica03;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class EmpleadoDAO implements IDAOGeneral<EmpleadoPojo, Long> {

    @Override
    public EmpleadoPojo guardar(EmpleadoPojo pojo) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(pojo);
        transaction.commit();
        return pojo;
    }

    @Override
    public EmpleadoPojo modificar(EmpleadoPojo pojo, Long id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        EmpleadoPojo pojoToEdit = session.get(EmpleadoPojo.class, id);
        if (pojoToEdit != null) {
            pojoToEdit.setNombre(pojo.getNombre());
            pojoToEdit.setDireccion(pojo.getDireccion());
            pojoToEdit.setTelefono(pojo.getTelefono());
            session.update(pojoToEdit);

            transaction.commit();
            return pojoToEdit;
        } else {
            transaction.commit();
            return null;
        }
    }

    @Override
    public boolean eliminar(Long id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        EmpleadoPojo pojo = session.get(EmpleadoPojo.class, id);
        if (pojo != null) {
            session.delete(pojo);
            transaction.commit();
            return true;
        } else {
            transaction.commit();
            return false;
        }

    }

    @Override
    public EmpleadoPojo buscarById(Long id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        EmpleadoPojo pojo = session.get(EmpleadoPojo.class, id);

        transaction.commit();
        return pojo;
    }

    @Override
    public List<EmpleadoPojo> buscarAll() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder criteriaB = session.getCriteriaBuilder();
        CriteriaQuery<EmpleadoPojo> criteria = criteriaB.createQuery(EmpleadoPojo.class);
        Root<EmpleadoPojo> root = criteria.from(EmpleadoPojo.class);
        criteria.select(root);

        Query<EmpleadoPojo> query = session.createQuery(criteria);

        List<EmpleadoPojo> lstEmpleado = query.list();

        transaction.commit();
        return lstEmpleado;

    }
}
