#!/usr/local/bin/python
# -*- coding: utf-8 -*- 
# This creates an HTTP message
# with the content of BODY as the enclosed representation
# for the resource http://localhost:8080/foobar

import httplib
import json

conn = httplib.HTTPConnection("localhost", 8080)
headers = {"Content-type": "application/json"}

data = [
{
  "name":"Умида",
  "categories":[
   {"name":"Main course",
    "dishes":[{"name":"Блинчики с творожным фаршем","price":14000.00},
    		  {"name":"Манты на пару со сметаной","price":18400.00},
    		  {"name":"Плов по-узбекски","price":18500.00},
    		  {"name":"Самса с куриными крылышками","price":14000.00},
    		  {"name":"Самса(тесто слоёное, фарш из телятины)","price":17000.00},
    		  {"name":"Тефтели(свекла, морковь, лук зелёный, огурцы св., вода, сахар)","price":10600.00},
    		  {"name":"Филе скумбрии со шпинатом и помидорами(скумбрия с/м, помидоры св., майонез, сыр, шпинат)","price":15100.00},]},
   {"name":"Soup",
    "dishes":[{"name":"Рассольник ленинградский с курицей","price":9700.00},
    		  {"name":"Суп из овощей с курицей","price":8500.00}]},
   {"name":"Accompainiment",
    "dishes":[{"name":"Горчица","price":3100.00},
    		  {"name":"Каша гречневая с помидорами","price":6400.00},
    		  {"name":"Кетчуп","price":1300.00},
    		  {"name":"Майонез","price":2000.00},
    		  {"name":"Пюре картофельное с помидорами","price":8400.00},
    		  {"name":"Сметана","price":2900.00},
    		  {"name":"Соус розовый","price":1900.00},
    		  {"name":"Хрен","price":3100.00}]},
   {"name":"Salad",
    "dishes":[{"name":"Блинчики с семгой сл.солёной","price":17200.00},
    		  {"name":"Буженина с помидорами","price":12200.00},
    		  {"name":"Салат 'Нежность'(помидоры св., огурцы св., кукуруза, кр.мясо, перец сл.)","price":9000.00},
    		  {"name":"Салат из свежих помидоров с огурцами с заправкой(помидоры св., огурцы св., лук репч., масло оливк., уксус)","price":8500.00},
    		  {"name":"Салат овощной с колбасой(картофель, морковь, лук, колбаса, яйцо, огурцы св., горошек)","price":8800.00},
    		  {"name":"Яйца под майонезом(яйцо, майонез, помидоры, зелёный горошек, зелень)","price":8900.00},
    		  {"name":"Яйца фаршированные грибами(яйца, шампиньоны, лук репч., майонез, зелень)","price":6600.00}]},
   {"name":"Beverage",
    "dishes":[{"name":"Напиток апельсиновый","price":2800.00},
    		  {"name":"Сок 'Настоящий'","price":4600.00}]},
   {"name":"Set",
    "dishes":[{"name":"Вафельные трубочки 'Вивайли'","price":3100.00},
    		  {"name":"Десерт 'Умида'(клюква, сливки, зефир, грецкий орех, желатин, сахар)","price":19300.00},
    		  {"name":"Зефир бело-розовый(пачка)","price":11100.00},
    		  {"name":"Зефирка бело-розовая","price":3700.00},
    		  {"name":"Карамель на палочке 'GoodФрукт'","price":5300.00},
    		  {"name":"Конфета 'Баунти'","price":9300.00},
    		  {"name":"Крем 'Снежок'","price":11400.00},
    		  {"name":"Лепёшка","price":2500.00},
    		  {"name":"Лимон с сахаром","price":3100.00},
    		  {"name":"Мороженое","price":9500.00},
    		  {"name":"Печенье песочное 'Твикс'","price":9300.00},
    		  {"name":"Хлеб чёрный","price":400.00},
    		  {"name":"Чипсы 'Мегачипс'","price":8000.00},
    		  {"name":"Шок.батончик 'Сникерс'","price":9300.00},
    		  {"name":"Шоколад 'Алёнка' молочный","price":6700.00},
    		  {"name":"Шоколад 'Коммунарка'","price":6000.00}]}
  ]
},
{
  "name":"Смач",
  "categories":[
   {"name":"Soup",
    "dishes":[{"name":"Суп с фрикадельками","price":12000.00},
    		  {"name":"Щи кислые с грибами(овощи, шамп., сметана)","price":12000.00}]},
   {"name":"Main course",
    "dishes":[{"name":"Гречка отв.+ куриные кусочки в соусе","price":30000.00},
    		  {"name":"Гречка с шампиньонами ВЕГЕТАРИАНСКОЕ","price":30000.00},
    		  {"name":"Картофельное пюре + Бифштекс Смак","price":30000.00}]},
   {"name":"Salad",
    "dishes":[{"name":"Огурцы 'Пятиминутка'","price":15000.00},
    		  {"name":"Салат 'Летний'","price":15000.00}]},
   {"name":"Beverage",
    "dishes":[{"name":"Гречка отв. + куриные кусочки в соусе + Салат 'Летний'","price":40000.00},
    		  {"name":"Гречка с шампиньонами ВЕГЕТАРИАНСКОЕ + Огурцы 'Пятиминутка'(2)","price":40000.00},
    		  {"name":"Картофельное пюре + Бифштекс Смак(2) + Огурцы 'Пятиминутка'","price":40000.00}]},
   {"name":"Set",
    "dishes":[{"name":"Суп с фрикадельками(2); Картофельное пюре + Бифштекс Смак(2) + Огурцы 'Пятиминутка'(2)","price":45000.00},
    		  {"name":"Щи кислые с грибами(1); Гречка отв. + куриные кусочки в соусе(1) + Салат 'Летний'(1)","price":45000.00}]}
  ]
}
]

for s in data:
	#print s["name"]
    post_data = {"name":s["name"]}
    conn.request("POST", "/hotmeals/suppliers", json.dumps(post_data), headers)
    response = conn.getresponse()
    resp_data = json.loads(response.read())
    #print resp_data
    supp_id = resp_data["id"]
    print supp_id

    for c in s["categories"]:
        print c["name"]
        post_data = {"supplierId":supp_id,"name":c["name"]}
        conn.request("POST", "/hotmeals/category", json.dumps(post_data), headers)
        response = conn.getresponse()
        resp_data = json.loads(response.read())
        print resp_data
        cat_id = resp_data["id"]

        for d in c["dishes"]:
        	#print d["name"]
            post_data = {"supplierId":supp_id,"categoryId":cat_id,"name":d["name"],"price":d["price"]}
            conn.request("POST", "/hotmeals/suppliers/" + str(supp_id) + "/dishes", json.dumps(post_data), headers)
            response = conn.getresponse()
            resp_data = json.loads(response.read())
            print resp_data
            #pass

