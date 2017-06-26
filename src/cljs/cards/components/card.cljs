(ns cards.components.card)

(defmulti card-content #(:type %))

(defmethod card-content :twitter-user [c]
  [[:div.card-header
    [:h5.pt-2 (:value c)]]
   [:div.card-block
    [:p.card-text (:value c)]]])

(defn card [c]
  (apply vector
    :div.card.card-shadow.text-dark
    (card-content c)))
