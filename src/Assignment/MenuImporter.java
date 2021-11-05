public class MenuImporter {
	public MenuList importExcel(MenuList menu) {
		Item tmp_item = new Item();
		PromoSet tmp_set = new PromoSet();

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Dessert"));
		tmp_item.setName("McFlurry Oreo");
		tmp_item.setType("Dessert");
		tmp_item.setSaleCost(2.7f);
		tmp_item.setBaseCost(1.62f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Dessert"));
		tmp_item.setName("McFlurry Strawberry Shortcake/Mudpie");
		tmp_item.setType("Dessert");
		tmp_item.setSaleCost(3.0f);
		tmp_item.setBaseCost(1.7999999999999998f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Dessert"));
		tmp_item.setName("Sundae");
		tmp_item.setType("Dessert");
		tmp_item.setSaleCost(1.7f);
		tmp_item.setBaseCost(1.02f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Dessert"));
		tmp_item.setName("Vanilla cone");
		tmp_item.setType("Dessert");
		tmp_item.setSaleCost(0.8f);
		tmp_item.setBaseCost(0.48f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Dessert"));
		tmp_item.setName("ChocoCone");
		tmp_item.setType("Dessert");
		tmp_item.setSaleCost(1.0f);
		tmp_item.setBaseCost(0.6f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Beef"));
		tmp_item.setName("Classic Angus cheese burger");
		tmp_item.setType("Beef");
		tmp_item.setSaleCost(8.95f);
		tmp_item.setBaseCost(5.369999999999999f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Beef"));
		tmp_item.setName("Double cheeseburger");
		tmp_item.setType("Beef");
		tmp_item.setSaleCost(3.5f);
		tmp_item.setBaseCost(2.1f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Beef"));
		tmp_item.setName("Big Mac");
		tmp_item.setType("Beef");
		tmp_item.setSaleCost(5.75f);
		tmp_item.setBaseCost(3.4499999999999997f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Beef"));
		tmp_item.setName("The original Angus");
		tmp_item.setType("Beef");
		tmp_item.setSaleCost(6.2f);
		tmp_item.setBaseCost(3.7199999999999998f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Fish"));
		tmp_item.setName("Double Filet-O-Fish");
		tmp_item.setType("Fish");
		tmp_item.setSaleCost(5.4f);
		tmp_item.setBaseCost(3.24f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Salads & Wraps"));
		tmp_item.setName("Grilled chicken salad");
		tmp_item.setType("Salads & Wraps");
		tmp_item.setSaleCost(5.8f);
		tmp_item.setBaseCost(3.48f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Salads & Wraps"));
		tmp_item.setName("Grilled chicken wrap");
		tmp_item.setType("Salads & Wraps");
		tmp_item.setSaleCost(5.8f);
		tmp_item.setBaseCost(3.48f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Chicken"));
		tmp_item.setName("McSpicy – single");
		tmp_item.setType("Chicken");
		tmp_item.setSaleCost(5.25f);
		tmp_item.setBaseCost(3.15f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Chicken"));
		tmp_item.setName("McSpicy – double");
		tmp_item.setType("Chicken");
		tmp_item.setSaleCost(7.2f);
		tmp_item.setBaseCost(4.32f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Chicken"));
		tmp_item.setName("Buttermilk crispy chicken");
		tmp_item.setType("Chicken");
		tmp_item.setSaleCost(6.95f);
		tmp_item.setBaseCost(4.17f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Chicken"));
		tmp_item.setName("McWings – 4pc");
		tmp_item.setType("Chicken");
		tmp_item.setSaleCost(4.2f);
		tmp_item.setBaseCost(2.52f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Chicken"));
		tmp_item.setName("Chicken nuggets – 6pc");
		tmp_item.setType("Chicken");
		tmp_item.setSaleCost(4.6f);
		tmp_item.setBaseCost(2.76f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Chicken"));
		tmp_item.setName("Chicken nuggets – 9pc");
		tmp_item.setType("Chicken");
		tmp_item.setSaleCost(6.2f);
		tmp_item.setBaseCost(3.7199999999999998f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Drinks"));
		tmp_item.setName("Coke");
		tmp_item.setType("Drinks");
		tmp_item.setSaleCost(2.0f);
		tmp_item.setBaseCost(1.2f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Drinks"));
		tmp_item.setName("Sprite");
		tmp_item.setType("Drinks");
		tmp_item.setSaleCost(2.0f);
		tmp_item.setBaseCost(1.2f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Drinks"));
		tmp_item.setName("Orange Juice");
		tmp_item.setType("Drinks");
		tmp_item.setSaleCost(2.5f);
		tmp_item.setBaseCost(1.5f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Sides"));
		tmp_item.setName("French Fries Small");
		tmp_item.setType("Sides");
		tmp_item.setSaleCost(1.9f);
		tmp_item.setBaseCost(1.14f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Sides"));
		tmp_item.setName("French Fries Medium");
		tmp_item.setType("Sides");
		tmp_item.setSaleCost(2.4f);
		tmp_item.setBaseCost(1.44f);
		menu.appendItem(tmp_item);

		tmp_item = new Item();
		tmp_item.setId(menu.getNewItemId("Sides"));
		tmp_item.setName("French Fries Large");
		tmp_item.setType("Sides");
		tmp_item.setSaleCost(2.5f);
		tmp_item.setBaseCost(1.5f);
		menu.appendItem(tmp_item);
		return menu;
	}
}