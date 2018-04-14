insert into users.User set id = 1, login = "tester", password = "qwerty", availableChests = 10, gold = 2000;

insert into quests.Quest set id = 1, date = now(), title = "Rowerowy wyścig", description = "Czy zabrałeś ze sobą rower?", type = "BIKE", value = 10;
insert into quests.Quest set id = 2, date = now(), title = "Siedmiokilometrowe buty", description = "Dogoń swoje przeznaczenie", type = "RUN", value = 7;
insert into quests.Quest set id = 3, date = now(), title = "Burn it down", description = "Kalorie - czy je spalisz?", type = "KCAL", value = 400;

insert into shop.Coupon set id = 1, description = "Talon na balon", title = "Z tym kuponem możesz odebrać darmowy balon w każdym sklepie z balonami", gold = 1500;
insert into shop.Coupon set id = 2, description = "Kawa w BIOWAY", title = "Odbierz darmową kawę do posiłku w BIOWAY", gold = 5300;
insert into shop.Coupon set id = 3, description = "Lorem ipsum", title = "Pamiętaj o uzupełnieniu tego opisu przed demo", gold = 4500;
