(ns cards.db)

(def default-db
  {:cards (map
            (fn [] {:content (apply str (->> "Lorem ipsum dolor sit amet"
                                             (repeat (rand 5))
                                             (interpose "\n")))})
            (range 8))})
