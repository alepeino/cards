(ns cards.components.card)

(defn card [i n]
  [:div.card {:key i}
   [:div.card-block
    (for [x (range (rand 5))]
      [:p {:key x} "Aasdsd asdasd dsd."])]])
