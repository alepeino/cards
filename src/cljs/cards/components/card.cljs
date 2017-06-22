(ns cards.components.card)

(defn card [i c]
  [:div.card.card-shadow {:key i}
   [:div.card-block
    (map-indexed
      (fn [key content]
        [:p {:key key} content])
      (.split (:content c) "\n"))]])
