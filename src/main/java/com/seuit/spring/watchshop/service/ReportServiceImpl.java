package com.seuit.spring.watchshop.service;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seuit.spring.watchshop.entity.Report;

@Service
public class ReportServiceImpl implements ReportService{
	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@Transactional
	@Override
	public List<Object> showReport(String from, String till) {
		Session session = this.getSession();
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");

		
		Date dateformat = null;
		Date dateformat2 = null;
		String fmFromdate = null;
		String fmTodate = null;

		try {
			dateformat = format2.parse(from);
			dateformat2 = format2.parse(till);

			fmFromdate = format1.format(dateformat);
			fmTodate = format1.format(dateformat2);

		} catch (ParseException e) {
			e.printStackTrace();
		} 
//		String sql = "select new com.seuit.spring.watchshop.entity.Report(fi.name, ifnull(A.Paid, 0) + ifnull(B.Unpaid, 0), ifnull(A.Price, 0) + ifnull(B.Price, 0),"
//				+ " ifnull(A.Paid, 0), ifnull(A.Price, 0), ifnull(B.Unpaid, 0 ), ifnull(B.Price) )"  
//				+ " from Firm fi left join "
//				+ " (select new com.seuit.spring.watchshop.entity.Report(f.name as Name, sum(od.amount) as Paid, sum(o.price) as Price)"
//				+ " from Order o inner join OrderDetail od on o.id = od.orderO.id inner join Product p on od.productO.id = p.id inner join Firm f on p.firm.id = f.id"
//				+ " where o.orderStatusO.id = 1 and o.createAt between :fromdate and :todate group by f.id) as A on A.name = fi.name"
//				+ " left join ("
//				+ " select new com.seuit.spring.watchshop.entity.Report(f.name as Name, sum(od.amount) as Paid, sum(o.price) as Price)"
//				+ " from Order o inner join OrderDetail od on o.id = od.orderO.id inner join Product p on od.productO.id = p.id inner join Firm f on p.firm.id = f.id"
//				+ " where o.orderStatusO.id = 2 and o.createAt between :fromdate and :todate group by f.id) as B on A.Name = B.Name" ;
				
//		String sql = "select new com.seuit.spring.watchshop.entity.Report(f.name, sum(od.amount) as Paid, sum(o.price) as Price)"+
//				" from Order o inner join OrderDetail od on o.id = od.orderO.id inner join Product p on od.productO.id = p.id inner join Firm f on p.firm.id = f.id"+
//				" where o.orderStatusO.id = 1 and o.createAt between :fromdate and :todate group by f.id";
		String sql = "select fi.name, ifnull(A.Paid,0) + ifnull(B.Unpaid, 0) as 'Amount', ifnull(A.Price,0) + ifnull(B.Price, 0) as 'Price' ," + 
				" ifnull(A.Paid, 0) as 'Paid', ifnull(A.Price,0) as'PriceP',ifnull(B.Unpaid, 0) as 'Unpaid', ifnull(B.Price,0) as'PriceUP' " + 
				"from firm fi left join " + 
				"(select f.name as 'Name', sum(od.amount) as 'Paid' ,sum(o.price) as 'Price' " + 
				"from orders o inner join order_detail od on o.id_order = od.id_order " + 
				"inner join product p on od.id_product = p.id_product " + 
				"inner join firm f on p.id_firm = f.id_firm " + 
				"where o.id_order_status = 1 and (o.create_at between :fromdate AND :tilldate) " + 
				"group by f.id_firm) as A on fi.name = A.name left join " + 
				"(select f.name as 'Name', sum(od.amount) as 'Unpaid',sum(o.price) as 'Price' " + 
				"from orders o inner join order_detail od on o.id_order = od.id_order " + 
				"inner join product p on od.id_product = p.id_product " + 
				"inner join firm f on p.id_firm = f.id_firm " + 
				"where o.id_order_status = 2 and (o.create_at between :fromdate AND :tilldate) " +
				"group by f.id_firm) as B on A.Name = B.Name; ";
		
		
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("fromdate", fmFromdate);
		query.setParameter("tilldate", fmTodate);

		List<Object[]> rows = query.list();
		List<Object> listRp = new ArrayList<Object>();
		for(Object[] row : rows){
			Report rp = new Report();
			rp.setName(row[0].toString());
			rp.setAmount(Long.parseLong(row[1].toString()));
			rp.setPrice(Double.parseDouble(row[2].toString()));
			rp.setPaid(Long.parseLong(row[3].toString()));
			rp.setPaidPrice(Double.parseDouble(row[4].toString()));
			rp.setUnpaid(Long.parseLong(row[5].toString()));
			rp.setUnpaidPrice(Double.parseDouble(row[6].toString()));
			listRp.add(rp);
		}
		return listRp;
	}

	
}
