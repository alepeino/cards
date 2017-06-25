(ns cards.db)

(def default-db
  {:cards (map
            (fn [i]
              {:id i
               :title (str "Card " i)
               :content (apply str (->> "Lorem ipsum dolor sit amet"
                                        (repeat (rand 5))
                                        (interpose "\n")))})
            (range 8))})
