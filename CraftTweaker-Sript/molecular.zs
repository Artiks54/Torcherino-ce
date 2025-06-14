import mods.tce.TileRfMolecular

TileRfMolecular.addRecipe(input, output, energy);

TileRfMolecular.addRecipe(<minecraft:stone>*1, <minecraft:diamond_block>*2, 1000000000);
TileRfMolecular.addRecipe(<minecraft:wool:11>*1, <minecraft:diamond_block>*2, 1000000000);


//EXAMPLE:
//TileRfMolecular.addRecipe(<minecraft:stone>*1, <minecraft:diamond_block>*2, 1000000000);
//Input : <minecraft:stone>*1 -> stone, count 1;
//Output : <minecraft:diamond_block>*2 -> Diamond_block, Count 2;
//Energy : 1000000000 -> 1 billion
//Maximum energy MAX java Long value
//9,223,372,036,854,775,806