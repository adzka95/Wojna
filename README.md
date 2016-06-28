# Wojna
Male cwiczenie na wielowatkowosc w javie

Interfejs umożliwia łatwą obserwację zdarzeń zachodzącą w całym programie. W oknie widnieją dane: ilość Rycerzy w Królestwie, siła z jaka Król wspiera Rycerzy, poziom szczęścia Księżniczki, ilość Giermków, Chłopów, ziemniaków, Górników,rudy złota i miedzi, ilość złota oraz błyskotek. Niżej wypisywane są komunikaty dla danego Królestwa. Na dole okna wypisywane są raporty z przeprowadzonych potyczek. To właśnie tam zostanie wypisany komunikat o rozstrzygnięciu całej wojny.

Chłop
Czeka wylosowana (od 1 do 11) liczbę sekund, następnie produkuje ziemniaka i dostarcza go do Stołówki.
Ma 1/7 szansy by w danej rundzie rozmnożyć się - stworzyć nowy wątek “Chlop”, oraz 1/10 szansy na to by stać się Giermkiem (wówczas wątek Chłop kończy działanie, a tworzy się watek Giermek).

Giermek
Czeka wylosowana (od 20 do 26) liczbę sekund, następnie udaje się do Stołówki. Jeśli w Stołówce znajduje się ziemniak to Giermek zwiększa swoja siłę (hp), jeśli nie to zmniejsza.

Górnik
Czeka wylosowana (od 6 do 26) liczbę sekund, następnie dodaje do Kopalni od 0 do 2 rudy miedzi i  od 0 do 2 rudy złota.

Huta
Co 5 sekund sprawdza czy w Kopalni jest 5 rudy złota i 4 rudy miedzi, jeśli jest to zabiera zasoby i tworzy złoto, jeśli nie to czeka do czasu gdy górnicy wyprodukują wystarczającą ilość surowców, następnie produkuje złoto. Jeśli jest więcej niż 2 złota w Hucie, budzony jest do życia Jubiler, jeśli oczekuje on w kolejce po złoto.

Jubiler
Co 10 sekund sprawdza czy w Hucie są 3 złota, jeśli nie to czeka na wyprodukowanie go, jeśli tak to produkuje błyskotki.

Kopalnia 
Magazynuje rude złota i rude miedzi. Jeśli rudy złota jest 5 lub więcej oraz rudy miedzi 4 lub więcej to budzi Hutę, jeśli czeka ona na materiały.

Król
Co 10 sekund sprawdza poziom szczęścia Księżniczki, a następnie 1 /3 wartości jej szczęścia umacnia każdego Rycerza.

Królestwo
Tutaj tworzone i uruchamiane są wszystkie wątki (oprócz Walki) w określonej za pomocą parametrów ilości. Tworzone są tu też odpowiednie dla konkretnego Królestwa elementy okienka. Znajdują się tu metody umożliwiające zmianę ich w trakcie działania programu.

Księżniczka
Czeka od 5 do kaprysyKsiezniczki * 5 sekund, a następnie udaje się do Jubilera po od 1 do 3 błyskotki. Jeśli Jubiler posiada wystarczającą ilość błyskotek to szczęście Księżniczki wzrasta o 5* ilość błyskotek jaka otrzymała, w przeciwnym razie jej szczęście obniża się o 2.

Plaga
Czeka od 20 do 30 sekund, następnie koczy działanie od 0 do 4 wątków losowych Chłopów oraz od 0 do 2 wątków losowych Rycerzy.

Rycerz
Czeka od 10 do 14 sekund, następnie idzie do Stołówki i zjada od 1 do 3 ziemniaków, jeśli taka ilość znajduje się w Stołówce, wzmacniając w ten sposób swoją siłę, jeśli nie jego siła maleje o 2.

Stołówka
Magazynuje ziemniaki, przyjmuje Chłopów co 0,75s.

Walka
Co 10 sekund losuje po Rycerzu z dwóch Królestw. Jeśli Królestwo nie posiada już żadnego Rycerza to przegrywa wojnie. Każdy Rycerz ma 1 /4, by jego siła została wzmocniona przez losowego Giermka z jego Królestwa. To Królestwo którego łączna siła Rycerza i ewentualnego Giermka jest większa wygrywa turę, a drużyna drugiego Królestwa ginie. Jeśli w wygranej drużynie znajduje się Giermek staje się on Rycerzem. W przypadku remisu obie drużyny giną.
Wojna
Tworzy dwa Królestwa oraz inicjuje Walkę miedzy nimi.


