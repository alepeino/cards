(ns cards.components.card)

(defn card [c]
  [:div.card.card-shadow.text-dark
   [:div.card-header
    [:h5.pt-2 (:title c)]]
   [:div.card-block
    (map-indexed
      (fn [key content]
        [:p.card-text {:key key} content])
      (.split (:content c) "\n"))]])
