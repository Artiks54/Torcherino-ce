import mods.tce.TileRfMolecular;

TileRfMolecular.addRecipe(<minecraft:wool:11>*1, <minecraft:diamond_block>*2, 10000000000);


//EXAMPLE DESCRIPTION:

//TileRfMolecular.addRecipe(input, output, energy);
//Maximum energy MAX java Long value
//9,223,372,036,854,775,806

//TileRfMolecular.addRecipe(<minecraft:wool:11>*1, <minecraft:diamond_block>*2, 10000000000);
//Input : <minecraft:wool:11>*1 -> wool, meta 11, count 1;
//Output : <minecraft:diamond_block>*2 -> Diamond_block, Count 2;
//Energy : 1000000000 -> 10 billion