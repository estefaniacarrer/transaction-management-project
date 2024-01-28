Classes:

⊳ Cliente</br>
  • Atributos:</br>
     • id (Long)</br>
     • nome (String)</br>
     • cpf (String)</br>
     
⊳ Empresa</br>
  • Atributos:</br>
     • id (Long)</br>
     • nome (String)</br>
     • cnpj (String)</br>
     • saldo (BigDecimal)</br>
     
⊳ Transacao</br>
  • Atributos:</br>
     • id (Long)</br>
     • tipo (TipoTransacao)</br>
     • valor (BigDecimal)</br>
     • data (LocalDateTime)</br>
     • cliente (Cliente)</br>
     • empresa (Empresa)</br>

Relacionamentos:</br>

Cliente 1 --- * Transacao: Um cliente pode ter várias transações.
</br>
Empresa 1 --- * Transacao: Uma empresa pode ter várias transações.


Diagrama:</br>



Tipos de Transações</br>

O atributo tipo da classe Transação é um tipo enumerado que representa os diferentes tipos de transações que podem ser realizadas no sistema. Os valores possíveis para este atributo são:</br>

DEPÓSITO: Representa um depósito realizado por um cliente.</br>
SAQUE: Representa um saque realizado por um cliente.</br>
