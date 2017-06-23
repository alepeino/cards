(ns cards.components.card)

(defn card [c]
  [:div.card.card-shadow.text-dark
   [:div.card-block
    (map-indexed
      (fn [key content]
        [:p {:key key} content])
      (.split (:content c) "\n"))]])
