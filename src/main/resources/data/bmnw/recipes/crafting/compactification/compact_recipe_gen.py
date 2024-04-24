#yay
import json

mod_id = "bmnw:"

def compact_recipe(ingredient, result):
    if "block" in result:
        category = "building"
    else:
        category = "misc"
    to_return = json.dumps({"type":"minecraft:crafting_shaped",
                            "category":category,
                            "group":result,
                            "key":{
                                "#":{
                                    "item":ingredient
                                }
                            },
                            "pattern":[
                                "###",
                                "###",
                                "###"
                            ],
                            "result":{
                                "item":result,
                                "count":1
                            },
                            "show_notification":True},
                           sort_keys = True, indent = 2)
    return to_return

def decompact_recipe(ingredient, result):
    category = "misc"
    to_return = json.dumps({"type":"minecraft:crafting_shaped",
                            "category":category,
                            "group":result,
                            "key":{
                                "#":{
                                    "item":ingredient
                                }
                            },
                            "pattern":[
                                "#"
                            ],
                            "result":{
                                "item":result,
                                "count":9
                            },
                            "show_notification":True},
                           sort_keys = True, indent = 2)
    return to_return

def register(ingot_id):
    toBlock = open("blocks/"+ingot_id+"_to_block.json", "w")
    fromBlock = open("blocks/"+ingot_id+"_from_block.json", "w")
    toNugget = open("nuggets/"+ingot_id+"_to_nuggets.json", "w")
    fromNugget = open("nuggets/"+ingot_id+"_from_nuggets.json", "w")
    toRawBlock = open("raw_blocks/"+ingot_id+"_to_block.json", "w")
    fromRawBlock = open("raw_blocks/"+ingot_id+"_from_block.json", "w")

    toBlock.write(compact_recipe(mod_id+ingot_id+"_ingot",
                                 mod_id+ingot_id+"_block"))
    toBlock.close()
    fromBlock.write(decompact_recipe(mod_id+ingot_id+"_block",
                                 mod_id+ingot_id+"_ingot"))
    fromBlock.close()
    toNugget.write(decompact_recipe(mod_id+ingot_id+"_ingot",
                                 mod_id+ingot_id+"_nugget"))
    toNugget.close()
    fromNugget.write(compact_recipe(mod_id+ingot_id+"_nugget",
                                 mod_id+ingot_id+"_ingot"))
    fromNugget.close()
    toRawBlock.write(compact_recipe(mod_id+"raw_"+ingot_id,
                                 mod_id+"raw_"+ingot_id+"_block"))
    toRawBlock.close()
    fromRawBlock.write(decompact_recipe(mod_id+"raw_"+ingot_id+"_block",
                                 mod_id+"raw_"+ingot_id))
    fromRawBlock.close()
    

#here register
register("uranium")
register("thorium")
register("bismuth")
register("steel")
register("conductive_copper")
