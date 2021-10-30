import pandas as pd

f = open("MenuImporter.java", "w")
f.write("public class MenuImporter {\n\
	public static MenuList importExcel(MenuList menu) {\n\
		Item tmp_item = new Item();\n\
		PromoSet tmp_set = new PromoSet();\n")
df = pd.read_excel ('menus.xlsx')
for i in range(df['name'].size):
	if df['item/set'][i] == 'item':
		f.write('\n\t\ttmp_item = new Item();\n')
		f.write('\t\ttmp_item.setId(menu.getNewItemId());\n')
		f.write('\t\ttmp_item.setName(\"'+df['name'][i]+'\");\n')
		f.write('\t\ttmp_item.setType(\"'+df['type'][i]+'\");\n')
		f.write('\t\ttmp_item.setSaleCost('+str(df['saleCost'][i])+'f);\n')
		f.write('\t\ttmp_item.setBaseCost('+str(df['baseCost'][i])+'f);\n')
		f.write('\t\tmenu.appendItem(tmp_item);\n')
f.write("\t\treturn menu;\n\t}\n}")
print (df['name'].size)

f.close()